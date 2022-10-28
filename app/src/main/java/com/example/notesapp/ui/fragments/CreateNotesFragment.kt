package com.example.notesapp.ui.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.model.Notes
import java.text.SimpleDateFormat
import java.util.*


class CreateNotesFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    var priority : String = "1"
    val viewmodel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(R.drawable.red_dot)
            binding.pYellow.setImageResource(R.drawable.yellow_dot)
        }
        binding.pYellow.setOnClickListener{
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(R.drawable.red_dot)
            binding.pGreen.setImageResource(R.drawable.green_dot)
        }
        binding.pRed.setOnClickListener{
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(R.drawable.green_dot)
            binding.pYellow.setImageResource(R.drawable.yellow_dot)
        }

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.title.text.toString()
        val subtitle = binding.subTitle.text.toString()
        val notes = binding.notes.text.toString()
        val df = SimpleDateFormat("dd-MMM-yyyy")
        val notesDate = df.format(Calendar.getInstance().time)

        val data = Notes(null,
            title=title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewmodel.addNotes(data)
        Toast.makeText(requireContext(), "Notes Created Successfully...!!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }


}