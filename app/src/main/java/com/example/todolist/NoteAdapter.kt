package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemlistNoteBinding

//initialize the adapter dataSet in the constructor
class NoteAdapter(dataSet: List<Repository.Note> = listOf()) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var mDataSet: List<Repository.Note> = dataSet
    private lateinit var mOnItemListClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener){
        mOnItemListClickListener = listener
    }

    inner class NoteViewHolder(val binding:ItemlistNoteBinding): RecyclerView.ViewHolder(binding.root){
        
        init { //constructor acts like onCreate method for itemlist
            itemView.setOnClickListener{
                mOnItemListClickListener.onClick(it,this.adapterPosition)
            }
        }

        //bind the data to the itemlist layout
        fun bind(note: Repository.Note){
            binding.txtViewNote.text = note.content
            Log.d("Adapter","setando texto do textView")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) //generates the layoutInflater
        val noteBinding = ItemlistNoteBinding.inflate(layoutInflater, parent,false) //generate the java object (view) of our itemList layout
        return NoteViewHolder(noteBinding) //pass the view's reference to the viewholder
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        viewHolder.bind(mDataSet[position])
    }

    override fun getItemCount(): Int = mDataSet.size

}