package com.example.myapplication.feature.dollar.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.feature.dollar.data.database.dao.IDollarDao
import com.example.myapplication.feature.dollar.data.database.entity.DollarEntity
import android.content.Context

@Database(entities = [DollarEntity::class], version = 1)
abstract class AppRoomDataBase: RoomDatabase() {
    abstract fun dollarDao(): IDollarDao

    companion object{
        @Volatile
        private var Instance: AppRoomDataBase? = null

        fun getDatabase(context: Context): AppRoomDataBase{
            return Instance?:synchronized(this){
                Room.databaseBuilder(context, AppRoomDataBase::class.java, "dollar_db")
                    .build()
                    .also { Instance=it }
            }
        }
    }
}