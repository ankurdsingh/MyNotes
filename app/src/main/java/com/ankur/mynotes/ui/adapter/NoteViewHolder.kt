package com.ankur.mynotes.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ankur.mynotes.databinding.ViewItemNoteBinding
import com.ankur.mynotes.util.Interactor

class NoteViewHolder(var binding: ViewItemNoteBinding,private val interactor: Interactor?)
    :RecyclerView.ViewHolder(binding.root){
    fun bind(){
        binding.root.setOnClickListener {
            interactor?.onItemSelected(adapterPosition,binding.note)
        }
    }
}