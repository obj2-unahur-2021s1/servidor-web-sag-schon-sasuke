package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensiones : MutableList<String>, val body: String, val tiempo: Int,var cantidadDeRespuestaDemoradas : Int = 0){

    fun puedeTrabajarCon(url: Pedido) = extensiones.contains(url.extension())

}