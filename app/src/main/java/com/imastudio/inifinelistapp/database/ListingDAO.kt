package com.imastudio.inifinelistapp.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imastudio.inifinelistapp.model.ItemEntityListing


@Dao
interface ListingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<ItemEntityListing>)

    @Query("SELECT * FROM ItemEntityListing")
    //buat datasource
    fun postDataSource() : DataSource.Factory<Int,ItemEntityListing>
}