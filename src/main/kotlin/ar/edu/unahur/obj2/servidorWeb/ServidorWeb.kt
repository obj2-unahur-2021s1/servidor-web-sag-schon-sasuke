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

    //No esta bien implementado pero bueno D:. Se puede mejorar
  fun codigoDeRespuesta() : CodigoHttp {
      return when {
         url.startsWith("http:") -> CodigoHttp.OK
         ip == "" -> CodigoHttp.NOT_FOUND
         else -> CodigoHttp.NOT_IMPLEMENTED
      }

  }

}




