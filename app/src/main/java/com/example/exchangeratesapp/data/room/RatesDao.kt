package com.example.exchangeratesapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularRate(rate: PopularRate)

    @Query("DELETE FROM popularRateTable")
    suspend fun deletePopularRates()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteRate(rate: FavouriteRate)

    @Query("DELETE FROM favouriteRateTable WHERE code = :code")
    suspend fun deleteFavouriteRates(code: String)

}