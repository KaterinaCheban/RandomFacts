package com.example.randomfacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.randomfacts.databinding.FragmentRandomFactBinding
import com.example.randomfacts.viewmodel.FactViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.randomfacts.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RandomFactFragment : Fragment() {

    private var _binding: FragmentRandomFactBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadFact()

        binding.buttonNextFact.setOnClickListener {
            viewModel.loadFact()
        }

        binding.buttonToHistory.setOnClickListener {
            findNavController().navigate(R.id.historyFragment)
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fact.collectLatest { fact ->
                binding.factText.text = fact?.text ?: "No fact found."

                fact?.text?.let {
                    saveFactToHistory(it)
                }
            }
        }

        binding.buttonFavorite.setOnClickListener {
            val currentFact = viewModel.fact.value?.text ?: return@setOnClickListener
            val prefs = requireContext().getSharedPreferences("favorites", 0)
            val set = prefs.getStringSet("facts", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

            if (set.contains(currentFact)) {
                set.remove(currentFact)
                binding.buttonFavorite.setImageResource(android.R.drawable.btn_star_big_off)
                Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                set.add(currentFact)
                binding.buttonFavorite.setImageResource(android.R.drawable.btn_star_big_on)
                Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
            }

            prefs.edit().putStringSet("facts", set).apply()

            binding.buttonToFavorites.setOnClickListener {
                findNavController().navigate(R.id.favoritesFragment)
            }

        }

    }


    private fun saveFactToHistory(text: String) {
        val prefs = requireContext().getSharedPreferences("fact_history", 0)
        val history = prefs.getString("facts", "") ?: ""
        val list = history.split("|").toMutableList()

        if (!list.contains(text)) {
            list.add(text)
            prefs.edit().putString("facts", list.joinToString("|")).apply()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
