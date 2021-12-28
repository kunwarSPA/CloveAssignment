package com.kotlin.cloverapp.cloveList.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clover.domain.clovelist.entity.response.CloveData
import com.kotlin.cloverapp.databinding.RecyclervieItemBinding

class CloveListAdapter(var context: Context, private var cloveData: CloveData) : RecyclerView.Adapter<CloveListAdapter.CustomViewHolder>() {
    private lateinit var binding: RecyclervieItemBinding
    private lateinit var mOnItemClickListener: View.OnClickListener
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = RecyclervieItemBinding.inflate(layoutInflater)
        return CustomViewHolder(binding, context,mOnItemClickListener)
    }

    override fun getItemCount(): Int {
        return cloveData.results.size
    }


    /**
     * view holder for row data
     */
    class CustomViewHolder(recyclerViewItemBinding: RecyclervieItemBinding, context: Context
                           ,mOnItemClickListener:View.OnClickListener):
        RecyclerView.ViewHolder(recyclerViewItemBinding.root) {
        private var mOnItemClickListener = mOnItemClickListener
        private var recyclerViewItemBinding=recyclerViewItemBinding
        private var context : Context = context
        fun bind(obj: CloveData, index: Int) {
            Glide.with(context).load(obj.results[index].image).centerCrop().into(
                recyclerViewItemBinding.imageView
            )  .clearOnDetach()
            recyclerViewItemBinding.name.text = obj.results[index].name //= obj.results[index].name
            recyclerViewItemBinding.species.text = obj.results[index].species
            recyclerViewItemBinding.status.text = obj.results[index].status
            recyclerViewItemBinding.root.tag = this
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mOnItemClickListener = itemClickListener
    }

    fun setFeedList(cloveData: CloveData){
        this.cloveData=cloveData
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(cloveData, position)
    }

}