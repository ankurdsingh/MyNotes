package com.ankur.mynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankur.mynotes.R
import com.ankur.mynotes.databinding.ViewItemNoteBinding
import com.ankur.mynotes.persistence.NoteModel
import com.ankur.mynotes.util.Interactor

class NoteAdapter(noteList: List<NoteModel>, private val interactor: Interactor)
    :RecyclerView.Adapter<NoteViewHolder>() {

    private val mDataList = mutableListOf<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewItemNoteBinding>(
            inflater, R.layout.view_item_note, parent,false)
        return NoteViewHolder(binding, interactor)
    }

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.note = mDataList[position]
        holder.bind()
    }

    fun swap(notes: List<NoteModel>) {
        val diffCallback = DiffCallback(this.mDataList, notes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.mDataList.clear()
        this.mDataList.addAll(notes)
        diffResult.dispatchUpdatesTo(this)
    }
}