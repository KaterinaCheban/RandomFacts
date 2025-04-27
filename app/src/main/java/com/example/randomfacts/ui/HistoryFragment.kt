package com.example.randomfacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomfacts.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val facts = mutableListOf<String>()
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(facts)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        loadFacts()
    }

//    private fun loadFacts() {
//        val prefs = requireContext().getSharedPreferences("fact_history", 0)
//        val set = prefs.getStringSet("facts", setOf()) ?: setOf()
//        facts.clear()
//        facts.addAll(set)
//        adapter.notifyDataSetChanged()
//    }

    private fun loadFacts() {
        val prefs = requireContext().getSharedPreferences("fact_history", 0)
        val saved = prefs.getString("facts", "") ?: ""
        facts.clear()
        facts.addAll(saved.split("|").filter { it.isNotBlank() })
        adapter.notifyDataSetChanged()

        binding.buttonClearHistory.setOnClickListener {
            val prefs = requireContext().getSharedPreferences("fact_history", 0)
            prefs.edit().clear().apply()

            facts.clear()
            adapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
