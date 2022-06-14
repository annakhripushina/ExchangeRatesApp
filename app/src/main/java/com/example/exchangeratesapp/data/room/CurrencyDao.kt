package com.example.exchangeratesapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularCurrency(currency: PopularCurrency)

    @Query("DELETE FROM popularCurrencyTable")
    suspend fun deletePopularCurrency()

    @Query("UPDATE popularCurrencyTable SET isFavourite = :isFavourite WHERE code = :code")
    suspend fun updateIsFavouriteCurrency(isFavourite: Boolean, code: String)

    @Query(
        "SELECT * FROM popularCurrencyTable " +
                "ORDER BY " +
                "CASE WHEN :codeSort = 1 THEN code END ASC, " +
                "CASE WHEN :codeSort = 2 THEN code END DESC, " +
                "CASE WHEN :valueSort = 1  THEN value END ASC, " +
                "CASE WHEN :valueSort = 2  THEN value END DESC"
    )
    fun getPopularCurrency(codeSort: Int?, valueSort: Int?): Flow<List<PopularCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCurrency(currency: FavouriteCurrency)

    @Query("DELETE FROM favouriteCurrencyTable WHERE code = :code")
    suspend fun deleteFavouriteCurrency(code: String)

    @Query("SELECT * FROM favouriteCurrencyTable")
    suspend fun getFavouriteList(): List<FavouriteCurrency>

    @Query(
        "SELECT f.code, p.value, p.isFavourite FROM favouriteCurrencyTable f, popularCurrencyTable p " +
                "WHERE f.code = p.code " +
                "ORDER BY " +
                "CASE WHEN :codeSort = 1 THEN f.code END ASC, " +
                "CASE WHEN :codeSort = 2 THEN f.code END DESC, " +
                "CASE WHEN :valueSort = 1  THEN p.value END ASC, " +
                "CASE WHEN :valueSort = 2  THEN p.value END DESC"
    )
    fun getFavouriteCurrency(codeSort: Int?, valueSort: Int?): Flow<List<PopularCurrency>>
}