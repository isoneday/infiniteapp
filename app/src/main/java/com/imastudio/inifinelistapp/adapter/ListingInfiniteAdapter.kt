package com.imastudio.inifinelistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.imastudio.inifinelistapp.R
import com.imastudio.inifinelistapp.model.ItemEntityListing
import kotlinx.android.synthetic.main.listitem.view.*

class ListingInfiniteAdapter :
    PagedListAdapter<ItemEntityListing, ListingViewHolder>(CallBackListing()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem, parent, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val item = getItem(position)
        val resource = holder.itemView.context.resources
        val score = resource.getString(R.string.score, item?.score)
        val comment = resource.getString(R.string.comment, item?.comments)
        holder.itemView.title.text = item?.title
        holder.itemView.score.text = score
        holder.itemView.comments.text = comment

    }

}