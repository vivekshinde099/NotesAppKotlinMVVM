package com.example.notesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewmodel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)


        viewmodel.getNotes().observe(viewLifecycleOwner,{notesList->
          binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)

        })

        binding.filterHigh.setOnClickListener{

            viewmodel.getHighNotes().observe(viewLifecycleOwner,{notesList->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)

            })
        }

        binding.allNotes.setOnClickListener{
            viewmodel.getNotes().observe(viewLifecycleOwner,{notesList->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)

            })
        }

        binding.filterMedium.setOnClickListener{

            viewmodel.getMediumNotes().observe(viewLifecycleOwner,{notesList->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)

            })
        }

        binding.filterLow.setOnClickListener{

            viewmodel.getLowNotes().observe(viewLifecycleOwner,{notesList->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)

            })
        }
        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }
}

