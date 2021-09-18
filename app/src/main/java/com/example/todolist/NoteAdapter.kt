package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemlistNoteBinding

//initialize the adapter dataSet in the constructor
class NoteAdapter(dataSet: List<Note> = listOf()) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val TAG = "NoteAdapter" //tag for log
    private var mDataSet: List<Note> = dataSet
    private lateinit var mOnItemListClickListener: OnItemClickListener

    fun getNoteList() = mDataSet

    fun setOnItemClickListener(listener: OnItemClickListener){
        mOnItemListClickListener = listener
    }

    inner class NoteViewHolder(private val binding:ItemlistNoteBinding): RecyclerView.ViewHolder(binding.root){

        init { //constructor acts like onCreate method for itemlist
            itemView.setOnClickListener{
                Log.i(TAG,"Listitem #${adapterPosition+1} clicked")
                mOnItemListClickListener.onClick(it,this.adapterPosition)
            }
        }

        //bind the data to the itemlist layout
        fun bind(note: Note){
            Log.d(TAG,"value of note ${note.content}")
            val lines = note.content.split("\n").filter { it.isNotEmpty() }

            binding.txtViewContent.text = ""
            binding.txtViewTitle.text = note.title //setting note's title

            with(binding.txtViewContent){
                var lineCounter = 0  //line counter
                for(line in lines) {
                    append("$line\n")
                    lineCounter++
                    if (lineCounter == 10) { //on line 11 (counter=10)
                        append("...")
                        break
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) //generates the layoutInflater
        val noteBinding = ItemlistNoteBinding.inflate(layoutInflater, parent,false) //generate the java object (view) of our itemList layout
        return NoteViewHolder(noteBinding) //pass the view's reference to the viewholder
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        Log.d(TAG,"value ${mDataSet[position]}")
        viewHolder.bind(mDataSet[position])
        Log.d(TAG,"viewholder #${viewHolder.adapterPosition+1} bounded")
    }

    override fun getItemCount(): Int = mDataSet.size

}