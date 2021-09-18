package com.example.todolist

import android.util.Log
import java.io.Serializable

data class Note(var title: String, var content: String): Serializable{
    private val id: Long = ++noteIdManager

    companion object{
        private var noteIdManager: Long = 0
    }

    fun getNoteId(): Long = id
}

class Repository {
    private val TAG = "Repository" //tag for log

    companion object{
        private var mNoteList = mutableListOf<Note>(
            //initial example notes
//            Note("Compras","pão\nfermento\nmussarela\npanetone\nframboesa\naspargos"),
//            Note("Presentes","superman\nbarbie\ncarrinho\nroupa\nbonecos\nfilmes\nanimes\ncomida\nchurrasco\nviagem\npiquenique\nabraço\nsei lá bicho"),
//            Note("Ligações para fazer hoje","mãe\nnamorada da Mais Impressionante Abobada Pessoa da face da terrar ENCHOTADA de carranco e cascas das lascas das patas dos tétanos dos bois, junto aos bodes, dois a dois, e slá es foram eles.\nCatarina\nVovó\nsobrinho chato")
        )
    }

    fun getNoteList(): List<Note> = mNoteList

    fun addNote(note: Note) {
        mNoteList.add(note)
        Log.i(TAG, "'addNote' method executed")
    }

    fun updateNote(note: Note) {
        with(mNoteList){
            val index = indexOf(find { it.getNoteId() == note.getNoteId() })
            this[index] = note
        }
        Log.i(TAG, "'updateNote' method executed")
    }

}