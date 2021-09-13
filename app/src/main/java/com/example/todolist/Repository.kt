package com.example.todolist


class Repository {
    data class Note(var title: String, var content: String)

    private var mNoteList = mutableListOf<Note>(
        Note("Compras","pão\nfermento\nmussarela\npanetone\nframboesa\naspargos"),
        Note("Presentes","superman\nbarbie\ncarrinho\nroupa"),
        Note("Ligações para fazer hoje","mãe\nnamorada da Sys\nCatarina\nVovó\nsobrinho chato")
    )

    fun getNoteList(): List<Note> = mNoteList

    fun addNote(note: Note) = mNoteList.add(note)
}