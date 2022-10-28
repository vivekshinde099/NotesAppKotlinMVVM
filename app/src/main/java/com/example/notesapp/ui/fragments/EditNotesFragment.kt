package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.example.notesapp.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {
    var priority : String = "1"


    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    val viewmodel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" ->{
                priority = "1"
                binding.edtGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.edtRed.setImageResource(R.drawable.red_dot)
                binding.edtYellow.setImageResource(R.drawable.yellow_dot)
            }
            "2" ->{
                priority = "2"
                binding.edtYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.edtRed.setImageResource(R.drawable.red_dot)
                binding.edtGreen.setImageResource(R.drawable.green_dot)
            }
            "3" ->{
                priority = "3"
                binding.edtRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.edtGreen.setImageResource(R.drawable.green_dot)
                binding.edtYellow.setImageResource(R.drawable.yellow_dot)
            }
        }

        binding.btnEditSaveNotes.setOnClickListener{

            updateNotes(it)
        }



        return binding.root


    }

    private fun updateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val df = SimpleDateFormat("dd-MMM-yyyy")
        val notesDate = df.format(Calendar.getInstance().time)

        val data = Notes(
            oldNotes.data.id,
            title=title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewmodel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes Updated Successfully...!!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete)
        {
            val BottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            BottomSheet.setContentView(R.layout.dialogue_delete)
            BottomSheet.show()

            val textviewYes = BottomSheet.findViewById<TextView>(R.id.dialog_Yes)
            val textviewNo = BottomSheet.findViewById<TextView>(R.id.dialog_No)

            textviewYes?.setOnClickListener {

                viewmodel.deleteNotes(oldNotes.data.id!!)
                BottomSheet.dismiss()
            }

            textviewNo?.setOnClickListener {
                BottomSheet.dismiss()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}