package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime


class Pedido(val ip: String, val url: String, val fechaHora: LocalDateTime,var cuantosPedidosRealizo : Int = 0){

    fun protocolo() = url.substringBefore("://")

    fun ruta() = url.substringAfter(".com.ar")

    fun extension() = url.substringAfterLast(".")

}


