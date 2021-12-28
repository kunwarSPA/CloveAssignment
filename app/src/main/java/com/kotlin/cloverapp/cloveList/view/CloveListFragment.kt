package com.kotlin.cloverapp.cloveList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.entity.response.Info
import com.clover.domain.clovelist.entity.response.Result
import com.clover.domain.clovelist.result.APIResult
import com.kotlin.cloverapp.R
import com.kotlin.cloverapp.cloveList.viewmodel.CloveListViewModel
import com.kotlin.cloverapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CloveListFragment : Fragment() {

    private val viewModel: CloveListViewModel by viewModels()
    lateinit var mCustomRecyclerAdapter: CloveListAdapter
    private lateinit var binding: FragmentListBinding
    private lateinit var cloveData: CloveData
    lateinit var navController: NavController

    private val onItemClickListener =
        View.OnClickListener { view ->
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.adapterPosition
            val thisItem: Result = cloveData.results.get(position)
            val args= bundleOf("ResultData" to thisItem
            )
            navController.navigate(R.id.navigateToDetailFragment,args)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel.cloveuseCaseLiveData.observe(viewLifecycleOwner, ::userStatusUpdate)
        val info = Info(0, "", 1, 0)
        val cloveData =  CloveData(info, listOf())
        mCustomRecyclerAdapter= activity?.let { CloveListAdapter(it.applicationContext, cloveData) }!!
        binding.recyclerView.layoutManager = LinearLayoutManager(container?.context)
        binding.recyclerView.adapter = mCustomRecyclerAdapter
        mCustomRecyclerAdapter.setOnItemClickListener(onItemClickListener);
        viewModel.getCloveData()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun userStatusUpdate(result: APIResult<CloveData>) {
        when (result) {
            is APIResult.Loading -> {
                print("Loading")
            }
            is APIResult.Success -> {
                cloveData = result.data
                mCustomRecyclerAdapter.setFeedList(result.data)
                mCustomRecyclerAdapter.notifyDataSetChanged()

            }
            is APIResult.Failure -> {
                Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}