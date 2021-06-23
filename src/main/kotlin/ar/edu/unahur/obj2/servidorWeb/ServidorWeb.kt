package ar.edu.unahur.obj2.servidorWeb

// Para no tener los códigos "tirados por ahí", usamos un enum que le da el nombre que corresponde a cada código
// La idea de las clases enumeradas es usar directamente sus objetos: CodigoHTTP.OK, CodigoHTTP.NOT_IMPLEMENTED, etc
enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}
class  ServidorWeb() {
    private val modulos = mutableListOf<Modulo>()
     val analizadores = mutableListOf<Analizador>()

    fun recibirPedido(pedido: Pedido): Respuesta {
        var respuesta : Respuesta = Respuesta(CodigoHttp.NOT_IMPLEMENTED, "", 10, pedido)

        if (pedido.protocolo() != "http" && !this.algunModuloSoporta(pedido)) {
            respuesta = Respuesta(CodigoHttp.NOT_FOUND, "", 10, pedido)
        }

       if (this.algunModuloSoporta(pedido) && pedido.protocolo() == "http") {
           val moduloSeleccionado : Modulo = this.modulos.find { it.puedeTrabajarCon(pedido) }!!
           respuesta =  Respuesta(CodigoHttp.OK, moduloSeleccionado.body, moduloSeleccionado.tiempo, pedido)

           if (this.analizadores.size > 0) {

               this.analizadores.forEach { it.recibeRespuestaYModulo(respuesta,moduloSeleccionado) }

           }

        } else {

            if (!this.algunModuloSoporta(pedido ) && pedido.protocolo() == "http") {
               respuesta = Respuesta(CodigoHttp.NOT_FOUND, "", 10, pedido)

            }
        }

        return  respuesta
    }

    fun agregarModulos(modulo: Modulo){
        if (!modulos.contains(modulo)){
        modulos.add(modulo)
        }
    }


    fun quitarModulo(modulo: Modulo){
        if (modulos.contains(modulo)){
        modulos.remove(modulo)
        }
    }

    fun agregarAnalizador(analizador: Analizador) {
        if (!analizadores.contains(analizador)) {
            analizadores.add(analizador)
        }
    }
    fun quitarAnalizador(analizador: Analizador){
        if (analizadores.contains(analizador)){
            analizadores.remove(analizador)
        }
    }

    fun algunModuloSoporta(url: Pedido) = this.modulos.any { it.puedeTrabajarCon(url) }

}

