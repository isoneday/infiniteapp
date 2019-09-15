package com.imastudio.inifinelistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imastudio.inifinelistapp.model.ItemEntityListing

@Database(entities = [ItemEntityListing::class],version = 1,exportSchema = false)
abstract class Listingdb : RoomDatabase(){

    companion object{
        fun create(context: Context) :Listingdb{
            val databaseBuilder = Room.databaseBuilder(context,
                Listingdb::class.java,"listing1.db")
            return databaseBuilder.build()
        }
    }

    abstract fun postDAO () : ListingDAO
}