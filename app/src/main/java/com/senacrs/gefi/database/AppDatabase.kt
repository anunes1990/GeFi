package com.senacrs.gefi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senacrs.gefi.daos.CartaoDao
import com.senacrs.gefi.model.Cartao

@Database(entities = arrayOf(Cartao::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun cartaoDao(): CartaoDao
}