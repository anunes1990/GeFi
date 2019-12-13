package com.senacrs.gefi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cartoes", indices = [Index("nome", unique = true)])
class Cartao(
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "nome")
    var nome: String,
    @ColumnInfo(name = "limite")
    var limite: Double,
    @ColumnInfo(name = "diaVencimento")
    var diaVencimento: Int,
    @ColumnInfo(name = "valorGasto")
    var valorGasto: Double

 ): Serializable