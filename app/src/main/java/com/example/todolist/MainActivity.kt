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
        //todo: add a constant to define the grid spanCount (to do it after figuring out what is it for)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecycler: RecyclerView
    private lateinit var mCurrentLayoutManager: LayoutType

    private enum class LayoutType{
        LINEAR_LAYOUT,
        GRID_LAYOUT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set the mRecycler variable and handle recyclerView configurations
        setupRecyclerView()
        setupListeners() //the listeners are not saved into the bundle, must be set to every Activity's creation

        //check if the Bundle is null, if true then LinearLayout is the default, else get the active list layout before the system's change
        mCurrentLayoutManager = (savedInstanceState?.getSerializable(KEY_LAYOUT_TYPE)?: LayoutType.LINEAR_LAYOUT) as LayoutType

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
    }

    private fun setListLayout(layoutType: LayoutType) {
        mCurrentLayoutManager = layoutType
        mRecycler.layoutManager = when(mCurrentLayoutManager){
            LayoutType.LINEAR_LAYOUT -> LinearLayoutManager(this)
            LayoutType.GRID_LAYOUT -> GridLayoutManager(this,2)
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