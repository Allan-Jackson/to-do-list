package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecycler: RecyclerView
    private lateinit var mCurrentLayoutManager: LayoutType

    private enum class LayoutType{
        LINEAR_LAYOUT,
        GRID_LAYOUT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setuping view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set the mRecycler variable and handle recyclerView configurations
        setupRecyclerView()


        if(savedInstanceState == null){
            mCurrentLayoutManager = LayoutType.LINEAR_LAYOUT
            setupListeners()
        }

        setListLayout(mCurrentLayoutManager)
    }

    private fun setupListeners() {
        binding.imgBtnListView.setOnClickListener(this)
        binding.imgBtnGridView.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        mRecycler = binding.rcylerViewNoteList
    }

    private fun setListLayout(layoutType: LayoutType) {
        mCurrentLayoutManager = layoutType
        mRecycler.layoutManager = when(mCurrentLayoutManager){
            LayoutType.LINEAR_LAYOUT -> LinearLayoutManager(this)
            LayoutType.GRID_LAYOUT -> GridLayoutManager(this,2)
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            binding.imgBtnGridView.id -> setListLayout(LayoutType.GRID_LAYOUT)
            binding.imgBtnListView.id -> setListLayout(LayoutType.LINEAR_LAYOUT)
        }
    }


}