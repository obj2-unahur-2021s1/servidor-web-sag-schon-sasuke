package ar.edu.unahur.obj2.servidorWeb

class Modulo(val extensiones : MutableList<String>, val body: String, val tiempo: Int){


    fun puedeTrabajarCon(url: String) = extensiones.any { ext -> url.endsWith(ext) }


}