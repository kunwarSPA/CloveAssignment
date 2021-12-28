package com.kotlin.employeeaccountapp.cloveList.view

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.entity.response.Info
import com.clover.domain.clovelist.entity.response.Result
import com.employee.domain.login.result.APIResult
import com.kotlin.employeeaccountapp.cloveList.viewmodel.CloveListViewModel
import com.kotlin.employeeaccountapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CloveListFragment : Fragment() {

    private val viewModel: CloveListViewModel by viewModels()
    lateinit var mCustomRecyclerAdapter: CloveListAdapter
    private lateinit var binding: FragmentListBinding
    private var isAllFabVisible: Boolean? = null
    private lateinit var cloveData: CloveData
   // private var mCallback: OnDashboardCallback? = null


    private val onItemClickListener =
        View.OnClickListener { view ->
            // This viewHolder will have all required values.
            val viewHolder = view.tag as RecyclerView.ViewHolder
            val position = viewHolder.adapterPosition
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            val thisItem: Result = cloveData.results.get(position)
            val args= arguments
            cloveData = (args!!.getSerializable("UserValidateObject") as CloveData?)!!
            Log.i("getuserValidate", cloveData.toString())
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

    private fun userStatusUpdate(result: APIResult<CloveData>) {
        when (result) {
            is APIResult.Loading -> {
                print("Loading")
            }
            is APIResult.Success -> {
                cloveData = result.data
                mCustomRecyclerAdapter.setFeedList(result.data)
                mCustomRecyclerAdapter.notifyDataSetChanged()

                // mCallback?.navigateToDashboardPage()
            }
            is APIResult.Failure -> {
                Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object {

        val FRAGMENT_NAME = CloveListFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            CloveListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}