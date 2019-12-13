package com.senacrs.gefi.daos

import androidx.room.*
import com.senacrs.gefi.model.Cartao

@Dao
interface CartaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartao(cartao: Cartao): Long

    @Update
    fun updateCartao(cartao: Cartao): Int

    @Delete
    fun deleteCartao(vararg cartao: Cartao)

    @Query("SELECT * FROM cartoes WHERE id == :id")
    fun cartoesById(id: Long): Cartao?

    @Query("SELECT * FROM cartoes")
    fun cartoesFindAll(): List<Cartao>

    @Query("SELECT sum(valorGasto) from cartoes")
    fun getTotalGasto():Double
}