package com.example.randomfacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomfacts.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favorites = mutableListOf<String>()
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFavorites()

        adapter = HistoryAdapter(favorites) { factToRemove ->
            val prefs = requireContext().getSharedPreferences("favorites", 0)
            val set = prefs.getStringSet("facts", setOf())?.toMutableSet() ?: mutableSetOf()
            set.remove(factToRemove)
            prefs.edit().putStringSet("facts", set).apply()

            val index = favorites.indexOf(factToRemove)
            if (index != -1) {
                favorites.removeAt(index)
                adapter.notifyItemRemoved(index)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }

    private fun loadFavorites() {
        val prefs = requireContext().getSharedPreferences("favorites", 0)
        val set = prefs.getStringSet("facts", setOf()) ?: setOf()
        favorites.clear()
        favorites.addAll(set)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
