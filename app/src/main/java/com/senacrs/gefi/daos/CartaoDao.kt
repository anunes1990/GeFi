package com.senacrs.gefi.daos

import androidx.room.*
import com.senacrs.gefi.model.Cartao

@Dao
interface CartaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartao(cartao: Cartao)

    @Update
    fun updateCartao(cartao:Cartao)

    @Delete
    fun deleteCartao(cartao:Cartao)

    @Query("SELECT * FROM cartoes WHERE id == :id")
    fun getCartaoId(id: Int): Cartao

    @Query("SELECT * FROM cartoes")
    fun getAllCartoes(): ArrayList<Cartao>

}