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

    fun recibirPedido(pedido: Pedido): Respuesta {
        var respuesta : Respuesta = Respuesta(CodigoHttp.NOT_IMPLEMENTED, "", 10, pedido)

        if (!pedido.url.startsWith("http:") && !this.algunModuloSoporta(pedido.url)) {
            respuesta = Respuesta(CodigoHttp.NOT_FOUND, "", 10, pedido)
        }

       if (this.algunModuloSoporta(pedido.url) && pedido.url.startsWith("http:")) {

            val moduloSeleccionado = this.modulos.find { it.puedeTrabajarCon(pedido.url) }!!
           respuesta =  Respuesta(CodigoHttp.OK, moduloSeleccionado.body, moduloSeleccionado.tiempo, pedido)

        } else {

            if (!this.algunModuloSoporta(pedido.url ) && pedido.url.startsWith("http:")) {
               respuesta = Respuesta(CodigoHttp.NOT_FOUND, "", 10, pedido)

            }
        }

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