package com.example.notesapp.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubTitle.setText(oldNotes.data.subtitle)
        binding.editNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.bg_green_700)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }

            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(R.drawable.bg_yellow_700)
                binding.pRed.setImageResource(0)
            }

            "3" -> {
                priority = "3"
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(R.drawable.bg_red_700)
            }
        }

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.bg_green_700)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(R.drawable.bg_yellow_700)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(R.drawable.bg_red_700)
        }

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", Date().time)

        val data = Notes(
            oldNotes.data.id,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT)
            .show()

        Navigation.findNavController(requireView())
            .navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete) {

            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<Button>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<Button>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_editNoteFragment_to_homeFragment)
//                startActivity(Intent(requireActivity(), HomeFragment::class.java))
                bottomSheet.dismiss()

            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}