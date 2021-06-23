package ar.edu.unahur.obj2.servidorWeb

class Analizador( val demoraMinima : Int = 12 ){
    val ipSospechosas = mutableSetOf<String>()
    val resgistroDeRespuestas = mutableSetOf<Respuesta>()

    fun seDemora(respuesta: Respuesta) = respuesta.tiempo > this.demoraMinima


    fun cuantosPedidosRealizo(ip: String) : Int {
        var cantidad : Int = 0
        if (resgistroDeRespuestas.any{ it.pedido.ip == ip }) {
            val ipseleccionada = resgistroDeRespuestas.find { it.pedido.ip == ip }!!
            cantidad = ipseleccionada.pedido.cuantosPedidosRealizo
        }
        return cantidad
    }

    fun recibeRespuestaYModulo(respuesta: Respuesta,modulo: Modulo){

        resgistroDeRespuestas.add(respuesta)

        if (this.seDemora(respuesta)){
            modulo.cantidadDeRespuestaDemoradas += 1
        }

        if (ipSospechosas.contains(respuesta.pedido.ip)){
            respuesta.pedido.cuantosPedidosRealizo += 1
        }

    }

    fun anadirIpSospechosa(ip :String){
        ipSospechosas.add(ip)
    }

}
