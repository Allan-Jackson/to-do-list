package com.example.todolist

import java.io.Serializable

data class Note(var title: String, var content: String): Serializable{
    private val id: Long = ++noteIdManager

    companion object{
        private var noteIdManager: Long = 0
    }

    fun getNoteId(): Long = id
}

class Repository {

    companion object{
        private var mNoteList = mutableListOf<Note>(
            Note("Compras","pão\nfermento\nmussarela\npanetone\nframboesa\naspargos"),
            Note("Presentes","superman\nbarbie\ncarrinho\nroupa"),
            Note("Ligações para fazer hoje","mãe\nnamorada da Sys\nCatarina\nVovó\nsobrinho chato")
        )
    }

    fun getNoteList(): List<Note> = mNoteList

    fun addNote(note: Note) = mNoteList.add(note)

    fun updateNote(note: Note) {
        with(mNoteList){
            val index = indexOf(find { it.getNoteId() == note.getNoteId() })
            this[index] = note
        }
    }

}