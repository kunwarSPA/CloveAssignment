package com.kotlin.cloverapp.cloveList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clover.domain.clovelist.entity.response.Result
import com.kotlin.cloverapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    lateinit var resultData: Result


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultData = arguments?.get("ResultData") as Result
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.name.text = resultData.name
        binding.dimension.text = resultData.origin.name
        binding.locationName.text = if (resultData.location.name.isNullOrEmpty()) "No Type Found" else resultData.location.name
        binding.nor.text =resultData.episode.size.toString()

        binding.type.text = resultData.type

        return binding.root
    }

}