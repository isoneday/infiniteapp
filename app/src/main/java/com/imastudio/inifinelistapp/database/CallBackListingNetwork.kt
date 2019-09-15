package com.imastudio.inifinelistapp.database

import android.util.Log
import androidx.paging.PagedList
import com.imastudio.inifinelistapp.helper.PagingRequestHelper
import com.imastudio.inifinelistapp.model.ItemEntityListing
import com.imastudio.inifinelistapp.model.ResponseListing
import com.imastudio.inifinelistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class CallBackListingNetwork(val database: Listingdb) : PagedList.BoundaryCallback<ItemEntityListing>() {

    val executor = Executors.newSingleThreadExecutor()
    val helper = PagingRequestHelper(executor)
    val api = InitRetrofit.getInstance()
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL){
        helperCallback ->

        api.getDataListing().enqueue(
            object : Callback<ResponseListing>{
                override fun onFailure(call: Call<ResponseListing>, t: Throwable) {
                    helperCallback.recordFailure(t)
                    Log.d("responseCallback :","failed to load data")
                }

                override fun onResponse(call: Call<ResponseListing>, response: Response<ResponseListing>) {
                helperCallback.recordSuccess()
                    Log.d("responseCallback :","Success to load data")
                val data = response.body()?.data?.children?.map {  it.data }
                    executor.execute{
                        database.postDAO().insert(data ?: listOf() )
                    }
                }

            }
        )
    }

    }

    override fun onItemAtEndLoaded(itemAtEnd: ItemEntityListing) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER){
                helperCallback ->

            api.getDataListing(after = itemAtEnd.name).enqueue(
                object : Callback<ResponseListing>{
                    override fun onFailure(call: Call<ResponseListing>, t: Throwable) {
                        helperCallback.recordFailure(t)
                        Log.d("responseCallback :","failed to load data")
                    }

                    override fun onResponse(call: Call<ResponseListing>, response: Response<ResponseListing>) {
                        helperCallback.recordSuccess()
                        Log.d("responseCallback :","Success to load data")
                        val data = response.body()?.data?.children?.map {  it.data }
                        executor.execute{
                            database.postDAO().insert(data ?: listOf() )
                        }
                    }

                }
            )
        }
    }
}
