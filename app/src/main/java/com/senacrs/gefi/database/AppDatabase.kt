package com.senacrs.gefi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.daos.CompraDao
import com.senacrs.gefi.model.Cartao
import com.senacrs.gefi.model.Compra

@Database(entities = [Cartao::class, Compra::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun cartaoDao(): CartaoDao
    abstract fun compraDao(): CompraDao
}