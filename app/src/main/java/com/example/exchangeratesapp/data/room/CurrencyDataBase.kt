package com.example.exchangeratesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency

@Database(entities = [PopularCurrency::class, FavouriteCurrency::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        private var instance: CurrencyDataBase? = null

        fun getDatabase(context: Context): CurrencyDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDataBase::class.java,
                    "currency-db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as CurrencyDataBase
        }
    }
}