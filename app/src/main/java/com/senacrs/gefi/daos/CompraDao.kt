package com.senacrs.gefi.daos

import androidx.room.*
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Compra

@Dao
interface CompraDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompra(compra: Compra): Long

    @Update
    fun updateCompra(compra: Compra): Int

    @Delete
    fun deleteCompra(vararg compra: Compra)

    @Query("SELECT * FROM compras WHERE id == :id")
    fun compraById(id: Long): Compra?

    @Query("SELECT * FROM compras WHERE idCartao == :idCartao")
    fun comprasCartaoFindAll(idCartao: Long): List<Compra>

}