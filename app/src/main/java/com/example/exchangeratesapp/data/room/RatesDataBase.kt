package com.example.exchangeratesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate

@Database(entities = [PopularRate::class, FavouriteRate::class], version = 1)
abstract class RatesDataBase : RoomDatabase() {

    abstract val ratesDao: RatesDao

    companion object {
        private var instance: RatesDataBase? = null

        fun getDatabase(context: Context): RatesDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RatesDataBase::class.java,
                    "rates-db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as RatesDataBase
        }
    }
}