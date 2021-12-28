package com.kotlin.employeeaccountapp.cloveList.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clover.domain.clovelist.entity.response.CloveData
import com.kotlin.employeeaccountapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cloveData: CloveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cloveData = (savedInstanceState!!.getSerializable("UserValidateObject") as CloveData?)!!
        Log.i("getuserValidate", cloveData.toString())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

}