package com.imastudio.inifinelistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.imastudio.inifinelistapp.adapter.CallBackListing
import com.imastudio.inifinelistapp.adapter.ListingInfiniteAdapter
import com.imastudio.inifinelistapp.database.CallBackListingNetwork
import com.imastudio.inifinelistapp.database.Listingdb
import com.imastudio.inifinelistapp.model.ItemEntityListing
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val adapter = ListingInfiniteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        toast("latihan 1")
        toast("latihan 2")
        toast("latihan 3")
        toast("latihan 33")
        toast("latihan 34")
//        toast("latihan 35")

    }

    private fun initList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        //config dari pagelist
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        
        val liveData = initPageListBuidler(config).build()
        liveData.observe(this, Observer<PagedList<ItemEntityListing>>{
            pagelist -> adapter.submitList(pagelist)
        })
    }

    private fun initPageListBuidler(config: PagedList.Config):
            LivePagedListBuilder<Int, ItemEntityListing> {
        val database  = Listingdb.create(this)
        val livePagedListBuilder = LivePagedListBuilder<Int,ItemEntityListing>(
            database.postDAO().postDataSource(),config
        )

        livePagedListBuilder.setBoundaryCallback(CallBackListingNetwork(database))
        return  livePagedListBuilder
    }
}
