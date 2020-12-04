package com.ankur.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ankur.mynotes.R
import com.ankur.mynotes.databinding.FragmentNotesBinding
import com.ankur.mynotes.persistence.NoteModel
import com.ankur.mynotes.ui.adapter.NoteAdapter
import com.ankur.mynotes.ui.viewmodel.NotesViewModel
import com.ankur.mynotes.util.Interactor
import com.ankur.mynotes.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import javax.inject.Inject

class NotesFragment : DaggerFragment(),Interactor {

    private lateinit var binding : FragmentNotesBinding
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var noteViewModel : NotesViewModel
    lateinit var noteAdapter: NoteAdapter
    lateinit var allNotes : List<NoteModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil
            .inflate(inflater,R.layout.fragment_notes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        initObject()
        observeData()
    }

    private fun initObject() {
        binding.fabAddNote.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.container)
                .navigate(R.id.action_notesFragment_to_createNoteFragment)
        }
        allNotes = mutableListOf()
        noteAdapter = NoteAdapter(allNotes, this)
        binding.rvNoteList.apply {
            adapter = noteAdapter
            val swipe = ItemTouchHelper(swipeToDelete())
            swipe.attachToRecyclerView(binding.rvNoteList)
        }
    }

    private fun setUpViewModel() {
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory)
            .get(NotesViewModel::class.java)
    }

    private fun observeData(){
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            it?.let {
                allNotes = it
                noteAdapter.swap(allNotes)
            }
        })
    }

    override fun onItemSelected(position: Int, note: NoteModel?) {
        val navDirections = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(note)
        findNavController().navigate(navDirections)
    }

    private fun swipeToDelete():ItemTouchHelper.SimpleCallback{
        return object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = allNotes[position]
                noteViewModel.deleteNote(note)
                Toast.makeText(activity,getString(R.string.note_deleted), Toast.LENGTH_SHORT).show()
            }

        }
    }
}