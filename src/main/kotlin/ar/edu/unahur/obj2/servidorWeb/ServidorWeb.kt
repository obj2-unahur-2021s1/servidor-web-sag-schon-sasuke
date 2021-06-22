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
    private val modulos = mutableListOf<Modulo>()

  fun codigoDeRespuesta(): Respuesta {
        var respuesta : Respuesta
        if (!url.startsWith("http:")) {
            respuesta =  Respuesta(CodigoHttp.NOT_IMPLEMENTED, "", 10,this)
        }
        if (this.algunModuloSoporta(url)) {
            val moduloSeleccionado = this.modulos.find { it.puedeTrabajarCon(url) }!!
            respuesta =  Respuesta(CodigoHttp.OK, moduloSeleccionado.text, moduloSeleccionado.tiempo, this)
        }
        respuesta = Respuesta(CodigoHttp.NOT_FOUND,"", 10,this)

        return  respuesta
  }

    fun agregarModulos(modulo: Modulo){
        modulos.add(modulo)
    }

    fun quitarModulo(modulo: Modulo){
        modulos.remove(modulo)
    }


    fun algunModuloSoporta(url: String) = this.modulos.any { it.puedeTrabajarCon(url) }

}




