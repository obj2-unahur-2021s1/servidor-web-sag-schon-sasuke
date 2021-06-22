package ar.edu.unahur.obj2.servidorWeb

class Analizador( val demoraMinima : Int = 12 ){

    fun seDemora(respuesta: Respuesta) = respuesta.tiempo > this.demoraMinima


}
