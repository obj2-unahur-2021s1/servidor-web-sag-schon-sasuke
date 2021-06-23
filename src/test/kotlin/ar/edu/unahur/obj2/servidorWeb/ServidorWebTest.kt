package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val servidorWeb = ServidorWeb()
    val pedido1 = Pedido("196.023.012","http://www.tooltyp.com/wp-content/uploads/2014/10/1900x920-8-beneficios-de-usar-imagenes-en-nuestros-sitios-web.jpg",LocalDateTime.now())
    val pedido2 = Pedido("196.023.013","https://campus-act.unahur.edu.ar/login/index.php",LocalDateTime.now())
    val pedido3 = Pedido("","https://www.apuntes.docx",LocalDateTime.now())
    val modulo1 = Modulo(mutableListOf<String>("jpg","doc","docx","gif"),"Imagenes y texto",14)
    val modulo2 = Modulo(mutableListOf<String>("jpg","png","rar","bin","php"),"Imagenes y cosas ",14)
    val analizador1 = Analizador()
    val analizador2 = Analizador()

    describe("Test CodigoHttp de pedido"){

      it("200 OK"){
        servidorWeb.agregarModulos(modulo1)
        val respuesta1 = servidorWeb.recibirPedido(pedido1)
        respuesta1.codigo.shouldBe(CodigoHttp.OK)
      }

      it("501 Not Implemented"){
        servidorWeb.agregarModulos(modulo2)
        val repuesta2 = servidorWeb.recibirPedido(pedido2)
        repuesta2.codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
      }

      it("404 Not Found"){
        val respuesta3 = servidorWeb.recibirPedido(pedido3)
        respuesta3.codigo.shouldBe(CodigoHttp.NOT_FOUND)
      }

    }

    describe("Test modulos") {

      describe("Puede responder al pedido con sus extensiones") {

        it("DOC") {
          val pedido = Pedido("196.023.012","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.doc",LocalDateTime.now())
          modulo1.puedeTrabajarCon(pedido)
            .shouldBe(true)
        }
        it("GIF") {
          val pedido = Pedido("196.123.012","https://campus-act.unahur.edu.ar/login/index.gif",LocalDateTime.now())
          modulo1.puedeTrabajarCon(pedido)
            .shouldBe(true)
        }
        it("JPG") {
          modulo1.puedeTrabajarCon(pedido1).shouldBe(true)
        }
        it("GITHUB") {
          val pedido = Pedido("196.023.045","https://Programacion.github",LocalDateTime.now())
          modulo1.puedeTrabajarCon(pedido).shouldBe(false)
        }
        it("PNG") {
          val pedido = Pedido("196.023.045","https://Programacion.png",LocalDateTime.now())
          modulo1.puedeTrabajarCon(pedido).shouldBe(false)
        }
      }

      describe("Hay alguno, o no "){

        it("hay"){
          servidorWeb.agregarModulos(modulo2)
          val respuesta4 = servidorWeb.recibirPedido(pedido1)

          respuesta4.codigo.shouldBe(CodigoHttp.OK)
          respuesta4.body.shouldBe(modulo2.body)
          respuesta4.pedido.shouldBe(pedido1)
          respuesta4.tiempo.shouldBe(modulo2.tiempo)
        }

        it("No hay"){
          val respuesta5 = servidorWeb.recibirPedido(pedido2)
          respuesta5.tiempo.shouldBe(10)
          respuesta5.pedido.shouldBe(pedido2)
          respuesta5.body.shouldBe("")
          respuesta5.codigo.shouldBe(CodigoHttp.NOT_FOUND)
        }
      }

      describe("Cantidad de respuestas demoradas"){
        servidorWeb.agregarModulos(modulo1)
        servidorWeb.agregarAnalizador(analizador1)

        it("1"){
          servidorWeb.recibirPedido(pedido1)
          modulo1.cantidadDeRespuestaDemoradas.shouldBe(1)
        }

        it("2"){
          servidorWeb.agregarModulos(modulo2)
          servidorWeb.agregarAnalizador(analizador2)
          servidorWeb.recibirPedido(pedido1)

          modulo1.cantidadDeRespuestaDemoradas.shouldBe(2)
        }
        it("0"){

          modulo2.cantidadDeRespuestaDemoradas.shouldBe(0)

        }

      }

    }
    describe("Test Analizadores"){
      describe("Pedidos realizados por ip sospechoza"){
        analizador1.anadirIpSospechosa("197.096.011")
        analizador1.anadirIpSospechosa("132.000.002")
        analizador1.anadirIpSospechosa("196.023.012")
        analizador1.anadirIpSospechosa("196.023.013")
        servidorWeb.agregarAnalizador(analizador1)
        servidorWeb.agregarModulos(modulo1)
        servidorWeb.recibirPedido(pedido1)

        it("1"){

          analizador1.cuantosPedidosRealizo("196.023.012").shouldBe(1)

        }

        it("2"){
          servidorWeb.recibirPedido(pedido1)
          analizador1.cuantosPedidosRealizo("196.023.012").shouldBe(2)

        }
      }
    }
  }
})
