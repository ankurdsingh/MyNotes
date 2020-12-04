package com.ankur.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ankur.mynotes.R
import com.ankur.mynotes.databinding.FragmentEditNoteBinding
import com.ankur.mynotes.persistence.NoteModel
import com.ankur.mynotes.ui.viewmodel.NotesViewModel
import com.ankur.mynotes.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class EditNoteFragment : DaggerFragment() {

    lateinit var binding: FragmentEditNoteBinding
    @Inject
    lateinit var viewModelProviderFactory : ViewModelProviderFactory
    lateinit var noteViewModel : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        initObject()
    }

    private fun initObject() {
        arguments?.let {
            val safeArgs = EditNoteFragmentArgs.fromBundle(it)
            val note = safeArgs.note
            binding.note = note
        }
        binding.btnEdit.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setUpViewModel() {
        noteViewModel = ViewModelProvider(requireActivity(),viewModelProviderFactory)
            .get(NotesViewModel::class.java)
    }

    override fun onDestroyView() {
        if(validateNoteBeforeSave()){
            updateNoteInDatabase()
            Toast.makeText(activity,getString(R.string.note_updated), Toast.LENGTH_SHORT).show()
        }else{
            binding.note?.let {
                noteViewModel.deleteNoteById(it.id)
                Toast.makeText(activity,getString(R.string.note_deleted), Toast.LENGTH_SHORT).show()
            }
        }
        super.onDestroyView()
    }

    private fun validateNoteBeforeSave(): Boolean {
        return !(binding.editTitle.text.isNullOrEmpty() &&
                binding.editDescription.text.isNullOrEmpty() &&
                binding.editTag.text.isNullOrEmpty())
    }

    private fun updateNoteInDatabase() {
        val title = if(binding.editDescription.text.isNullOrEmpty()) "Untitled" else binding.editTitle.text.toString()
        val oldId = binding.note?.id!!
        val note = NoteModel(oldId, title,
            binding.editDescription.text.toString(),
            System.currentTimeMillis(),
            binding.editTag.text.toString())
        // update note
        noteViewModel.updateNote(note)
    }
}