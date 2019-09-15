package com.imastudio.inifinelistapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.imastudio.inifinelistapp.model.ItemEntityListing

class CallBackListing : DiffUtil.ItemCallback<ItemEntityListing>(){

    override fun areItemsTheSame(oldItem: ItemEntityListing, newItem: ItemEntityListing): Boolean {
  return  oldItem.name ==newItem.name
    }

    override fun areContentsTheSame(oldItem: ItemEntityListing, newItem: ItemEntityListing): Boolean {
  return oldItem.title ==newItem.title &&
          oldItem.name ==newItem.name &&
          oldItem.score ==newItem.score &&
          oldItem.comments ==newItem.comments
    }
}