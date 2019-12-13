package com.senacrs.gefi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "compras")
class Compra(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "idCartao")
    val idCartao: Long,
    @ColumnInfo(name = "descricao")
    val descricao: String,
    @ColumnInfo(name = "valor")
    val valor: Double,
    @ColumnInfo(name = "vezes")
    val vezes:Int,
    @ColumnInfo(name = "parcelaAtual")
    val parcelaAtual: Int
): Serializable