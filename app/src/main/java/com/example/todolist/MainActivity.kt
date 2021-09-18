package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

const val EXTRA_NOTE = "com.example.todolist.NOTE_ID"

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity" //tag for log
    //class constants (do not depends on class instances)
    companion object{
        private const val KEY_LAYOUT_TYPE = "layoutType"
        private const val SPAN_COUNT = 2 //defines the quantity of view per line (in the grid visualization)
    }
    //todo: pesquisar sobre o padrão Repository e DI
    private val repository = Repository()
    private var mCurrentLayoutManager = LayoutType.LINEAR_LAYOUT
    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: NoteAdapter


    private enum class LayoutType{
        LINEAR_LAYOUT,
        GRID_LAYOUT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        //setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(savedInstanceState){
            if(this!=null){
                mCurrentLayoutManager = getSerializable(KEY_LAYOUT_TYPE) as LayoutType
            }
        }

        Log.i(TAG, "Activity created")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Activity has started")
        setupRecyclerView()
        setupListeners() //the listeners are not saved into the bundle, must be set to every Activity's creation
        setListLayout(mCurrentLayoutManager)
    }

    //saves the list layout when a system change occurs
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_LAYOUT_TYPE,mCurrentLayoutManager)
        Log.i(TAG, "'CurrentLayout Manager' saved into Bundle")
    }

    private fun setupListeners() {
        binding.imgViewListView.setOnClickListener(this)
        binding.imgViewGridView.setOnClickListener(this)
        binding.btnAddNote.setOnClickListener(this)
        mAdapter.setOnItemClickListener{ _,position -> //listener for RecyclerView's list item
            val selectedNote = mAdapter.getNoteList()[position]
            val intent = Intent(this,NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE,selectedNote)
            startActivity(intent)
            Log.i(TAG, "starting NoteActivity by clicking on a note;")
        }
        Log.i(TAG, "Listeners set")
    }

    private fun setupRecyclerView() {
        mRecycler = binding.rcylerViewNoteList
        mAdapter = NoteAdapter(repository.getNoteList())
        mRecycler.adapter = mAdapter
        Log.i(TAG,"RecyclerView's adapter set")
    }

    private fun setListLayout(layoutType: LayoutType) {
        mCurrentLayoutManager = layoutType
        mRecycler.layoutManager = when(mCurrentLayoutManager){
            LayoutType.LINEAR_LAYOUT -> LinearLayoutManager(this)
            LayoutType.GRID_LAYOUT -> GridLayoutManager(this,SPAN_COUNT)
        }
        Log.i(TAG,"RecyclerView's LayoutManager set to: $mCurrentLayoutManager")
    }

    override fun onClick(view: View) {
        when(view.id){
            binding.imgViewGridView.id -> setListLayout(LayoutType.GRID_LAYOUT)
            binding.imgViewListView.id -> setListLayout(LayoutType.LINEAR_LAYOUT)
            binding.btnAddNote.id -> {
                //an Intent provides runtime binding between separate components (as two activities)
                //The Intent represents an app’s intent to do something.
                startActivity(Intent(this,NoteActivity::class.java))
                Log.i(TAG,"Starting NoteActivity by button \"AddNote\"")
            }
        }
    }

    private fun showShortToast(text: String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Log.i(TAG, "Activity was destroyed")
        super.onDestroy()
    }




}