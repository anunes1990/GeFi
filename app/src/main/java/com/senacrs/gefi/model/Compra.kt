package com.senacrs.gefi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "compras")
class Compra(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "idCartao")
    var idCartao: Long,
    @ColumnInfo(name = "descricao")
    var descricao: String,
    @ColumnInfo(name = "valor")
    var valor: Double,
    @ColumnInfo(name = "vezes")
    var vezes:Int,
    @ColumnInfo(name = "parcelaAtual")
    var parcelaAtual: Int
): Serializable