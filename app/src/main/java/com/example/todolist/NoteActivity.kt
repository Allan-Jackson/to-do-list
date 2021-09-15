package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ArrowKeyMovementMethod
import android.util.Log
import android.view.MenuItem
import com.example.todolist.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    private val TAG = "NoteActivity"
    private var note: Note? = null
    private lateinit var binding: ActivityNoteBinding
    private val repository = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*MovimentMethod: Provides cursor positioning, scrolling and text selection functionality in a TextView.
        ArrowKeyMovementMethod -> A movement method that provides cursor movement and selection.
        ScrollingMovementMethod -> A movement method that interprets movement keys by scrolling the text buffer.
        LinkMovementMethod -> A movement method that traverses links in the text buffer and scrolls if necessary.*/
        //enable cursor moviment and selection, but do not allow scrolling
        binding.editTxtNoteContent.movementMethod = ArrowKeyMovementMethod()
        binding.editTxtNoteTitle.maxLines = 15
        binding.editTxtNoteTitle.setHorizontallyScrolling(false)


        note = intent.getSerializableExtra(EXTRA_NOTE)?.run { this as Note }
        binding.editTxtNoteTitle.text.append(note?.title?:"")
        binding.editTxtNoteContent.text.append(note?.content?:"")

      println(note)

        //sendind focus to the note content editText
        binding.frame.setOnClickListener {
            binding.frame.clearFocus()
            binding.editTxtNoteContent.requestFocus()
            //TODO: chamar o teclado
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        val title = binding.editTxtNoteTitle.text.toString()
        val content = binding.editTxtNoteContent.text.toString()

        if(note == null){
            repository.addNote(Note(title, content)) //add the new note to the repository list
        }else{
            note.also {
                if (it != null) {
                    it.content = content
                    it.title = title
                    repository.updateNote(it)
                }
            }
        }
        return super.onSupportNavigateUp()
    }

}