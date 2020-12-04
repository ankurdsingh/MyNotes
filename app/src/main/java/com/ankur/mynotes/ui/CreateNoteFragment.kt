package com.ankur.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ankur.mynotes.R
import com.ankur.mynotes.databinding.FragmentCreateNoteBinding
import com.ankur.mynotes.persistence.NoteModel
import com.ankur.mynotes.ui.viewmodel.NotesViewModel
import com.ankur.mynotes.util.ViewModelProviderFactory
import com.ankur.mynotes.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_note.view.*
import javax.inject.Inject


class CreateNoteFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelProviderFactory : ViewModelProviderFactory
    lateinit var binding : FragmentCreateNoteBinding
    lateinit var noteViewModel : NotesViewModel
    private lateinit var navController : NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_create_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        initObject()
    }

    private fun initObject() {
        navController = Navigation.findNavController(requireActivity(),R.id.container)
        binding.btnAdd.setOnClickListener {
            it?.let { hideKeyboard(it) }
            navController.popBackStack()
        }
    }

    private fun setUpViewModel() {
        noteViewModel = ViewModelProvider(requireActivity(),viewModelProviderFactory)
            .get(NotesViewModel::class.java)
    }

    override fun onDestroyView() {
        if(validateNoteBeforeSave()){
            saveNoteToDatabase()
            Toast.makeText(activity,getString(R.string.note_saved),Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity,getString(R.string.empty_note_not_saved),Toast.LENGTH_SHORT).show()
        }
        super.onDestroyView()
    }

    private fun validateNoteBeforeSave(): Boolean {
        return !(binding.addTitle.text.isNullOrEmpty() &&
            binding.addDescription.text.isNullOrEmpty() &&
            binding.addTag.text.isNullOrEmpty())
    }

    private fun saveNoteToDatabase() {
        val title = if(binding.addDescription.text.isNullOrEmpty()) "Untitled" else binding.addTitle.text.toString()
        val note = NoteModel(0, title,
            binding.addDescription.text.toString(),
            System.currentTimeMillis(),
            binding.addTag.text.toString())
        // insert note
        noteViewModel.saveNote(note)
    }

}