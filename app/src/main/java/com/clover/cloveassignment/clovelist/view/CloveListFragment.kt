package com.clover.cloveassignment.clovelist.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.clover.cloveassignment.databinding.CloveListFragmentBinding

class CloveListFragment  : Fragment() {
    private lateinit var binding: CloveListFragmentBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = CloveListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}