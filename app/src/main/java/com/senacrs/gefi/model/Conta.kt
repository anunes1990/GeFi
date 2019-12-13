package com.senacrs.gefi.model

import java.io.Serializable

class Conta(val idCartao:Int, val nome: String, val diaVencimento: Int,  val valor: Double):
    Serializable