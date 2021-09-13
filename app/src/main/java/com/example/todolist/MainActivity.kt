package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //class constants (do not depends on class instances)
    companion object{
        private const val KEY_LAYOUT_TYPE = "layoutType"
        private const val SPAN_COUNT = 2 //defines the quantity of view per line (in the grid visualization)
    }
    private var repository = Repository()
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

        //set the mRecycler variable and handle recyclerView configurations
        setupRecyclerView()
        setupListeners() //the listeners are not saved into the bundle, must be set to every Activity's creation

        with(savedInstanceState){
            if(this!=null){
                mCurrentLayoutManager = getSerializable(KEY_LAYOUT_TYPE) as LayoutType
            }
        }

        setListLayout(mCurrentLayoutManager)
    }

    //saves the list layout when a system change occurs
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_LAYOUT_TYPE,mCurrentLayoutManager)
    }

    /*  it's executed only after onStart(), if the data saved into the Bundle were required to Activity's re-creation,
        then it must be called on Activity's onCreate() method

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    */

    private fun setupListeners() {
        binding.imgViewListView.setOnClickListener(this)
        binding.imgViewGridView.setOnClickListener(this)
        binding.btnAddNote.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        mRecycler = binding.rcylerViewNoteList
        //TODO: needed to set the adapter
        mAdapter = NoteAdapter(repository.getNoteList())
        mAdapter.setOnItemClickListener{ _,position ->
            showShortToast("List item ${position+1} was clicked!")
        }
        mRecycler.adapter = mAdapter
    }

    private fun setListLayout(layoutType: LayoutType) {
        mCurrentLayoutManager = layoutType
        mRecycler.layoutManager = when(mCurrentLayoutManager){
            LayoutType.LINEAR_LAYOUT -> LinearLayoutManager(this)
            LayoutType.GRID_LAYOUT -> GridLayoutManager(this,SPAN_COUNT)
        }
        Log.d("MainActivity","RecyclerView LayoutManager is: ${mRecycler.layoutManager}")
    }

    override fun onClick(view: View) {
        when(view.id){
            binding.imgViewGridView.id -> setListLayout(LayoutType.GRID_LAYOUT)
            binding.imgViewListView.id -> setListLayout(LayoutType.LINEAR_LAYOUT)
            binding.btnAddNote.id -> {
                //TODO: open Edit/Add Note Activity
                showShortToast("this functionality is coming soon...please be patient;)")
            }
        }
    }

    private fun showShortToast(text: String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

}