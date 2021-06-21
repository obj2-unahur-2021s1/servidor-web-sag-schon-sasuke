package ar.edu.unahur.obj2.servidorWeb

import java.time.LocalDateTime

// Para no tener los códigos "tirados por ahí", usamos un enum que le da el nombre que corresponde a cada código
// La idea de las clases enumeradas es usar directamente sus objetos: CodigoHTTP.OK, CodigoHTTP.NOT_IMPLEMENTED, etc
enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

class Pedido(val ip: String, val url: String, val fechaHora: LocalDateTime){
    val modulos = mutableListOf<Modulo>()

    //No esta bien implementado pero bueno D:. Se puede mejorar
  fun codigoDeRespuesta(): Respuesta {
        val modulo : Modulo = modulos.find { it -> it.puedeTrabajarCon(url) }!!
        return when {
            url.startsWith("http:") && this.algunModuloSoporta(url) -> Respuesta(CodigoHttp.OK,modulo.text,modulo.tiempo,this)
            ip == "" || url  == "" && !this.algunModuloSoporta(url)-> Respuesta(CodigoHttp.NOT_FOUND,"",10,this)
            else -> Respuesta(CodigoHttp.NOT_IMPLEMENTED,"",10,this)
        }
  }
    fun algunModuloSoporta(url: String) = this.modulos.any { it.puedeTrabajarCon(url) }

}




