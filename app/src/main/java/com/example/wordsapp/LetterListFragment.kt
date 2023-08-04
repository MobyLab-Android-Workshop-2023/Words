package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

    private var isLinearLayoutManager = true
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentLetterListBinding? = null
    val binding: FragmentLetterListBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = _binding!!.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_switch_layout) {
            isLinearLayoutManager = !isLinearLayoutManager
            chooseLayout()
            setIcon(item)
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun chooseLayout() {
        recyclerView.layoutManager = if (isLinearLayoutManager)
            LinearLayoutManager(context)
        else GridLayoutManager(context, 4)

        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon = if (isLinearLayoutManager)
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
        else ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
    }
}