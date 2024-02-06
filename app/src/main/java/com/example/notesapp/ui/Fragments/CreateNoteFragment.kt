package com.example.notesapp.ui.Fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import java.util.Date

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        setColors(ContextCompat.getColorStateList(requireContext(), R.color.green_700), null, null)

        binding.pGreen.setOnClickListener {
            priority = "1"
            setColors(
                ContextCompat.getColorStateList(requireContext(), R.color.green_700),
                null,
                null
            )
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            setColors(
                null,
                ContextCompat.getColorStateList(requireContext(), R.color.yellow_700),
                null
            )
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            setColors(
                null,
                null,
                ContextCompat.getColorStateList(requireContext(), R.color.red_700)
            )
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes()
        }

        return binding.root
    }

    private fun setColors(green: ColorStateList?, yellow: ColorStateList?, red: ColorStateList?) {
        binding.pGreen.backgroundTintList = green
        binding.pYellow.backgroundTintList = yellow
        binding.pRed.backgroundTintList = red
    }

    private fun createNotes() {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", Date().time)

        val data = Notes(
            null,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_SHORT)
            .show()

        Navigation.findNavController(requireView()).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}
