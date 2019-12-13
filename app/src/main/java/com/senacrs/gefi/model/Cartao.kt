package com.senacrs.gefi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cartoes")
class Cartao(
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name = "nome")
    val nome: String,
    @ColumnInfo(name = "limite")
    val limite: Double,
    @ColumnInfo(name = "diaVencimento")
    val diaVencimento: Int
 ): Serializable