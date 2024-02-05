package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import java.util.Date

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setOnClickListener {

        }

        binding.pGreen.setOnClickListener {

        }

        binding.pGreen.setOnClickListener {

        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.editTitle.text
        val subTitle = binding.editSubTitle.text
        val notes = binding.editNotes.text
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", Date().time)

    }

}