package ar.edu.unahur.obj2.servidorWeb

class Analizador( val demoraMinima : Int = 12 ){
    val ipSospechosas = mutableListOf<Ip>()


    fun seDemora(respuesta: Respuesta) = respuesta.tiempo > this.demoraMinima


    fun cuantosHizoIp(ip: Ip) : Int {
        var cantidad : Int = 0

        if (ipSospechosas.contains(ip.ip)){
           cantidad = this.ipSospechosas.find { it.ip == ip.ip}!!.pedidosRealizados
        }
        return  cantidad
    }

    fun moduloMasConsultado(){

    }

    fun recibeRespuestaYModulo(respuesta: Respuesta,modulo: Modulo){

        this.seDemora(respuesta)
        ipSospechosas.forEach { it.realizoPedidos(respuesta.pedido.ip) }

        if (this.seDemora(respuesta)){
            modulo.cantidadDeRespuestaDemoradas += 1
        }

    }

}

class Ip(var ip : String, var pedidosRealizados : Int = 0){

    fun realizoPedidos(ip: String){
        if (this.ip == ip){
            this.pedidosRealizados +=1
        }
    }

}
