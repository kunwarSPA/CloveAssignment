package com.kotlin.employeeaccountapp.cloveList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.entity.response.Result
import com.kotlin.employeeaccountapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cloveData: CloveData
    lateinit var resultData: com.clover.domain.clovelist.entity.response.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultData = arguments?.get("ResultData") as Result
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.name.text = "NAME "+resultData.name
        binding.dimension.text = "DIMENSION "+resultData.origin.name
        binding.locationName.text = "Location Name " +if (resultData.location.name.isNullOrEmpty()) "No Type Found" else resultData.location.name
        binding.nor.text = "Number of resident  "+resultData.episode.size.toString()

        binding.type.text = resultData.type

        return binding.root
    }

}