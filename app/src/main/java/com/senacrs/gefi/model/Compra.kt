package com.senacrs.gefi.model

import java.io.Serializable

class Compra(val idCartao: Int, val descricao: String, val valor: Double, val vezes:Int, val parcelaAtual: Int): Serializable