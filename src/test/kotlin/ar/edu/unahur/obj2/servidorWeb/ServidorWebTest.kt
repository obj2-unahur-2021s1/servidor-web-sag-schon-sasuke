package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val servidorWeb = ServidorWeb()
    val pedido1 = Pedido("196.023.012","http://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.jpg",LocalDateTime.now())
    val pedido2 = Pedido("196.023.012","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())
    val pedido3 = Pedido("","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.docx",LocalDateTime.now())
    val modulo1 = Modulo(mutableListOf<String>("jpg","doc","docx","gif"),"Hola mi amor",14)

    describe("Test CodigoHttp de pedido"){

      it("200 OK"){
        servidorWeb.agregarModulos(modulo1)
        val respuesta1 = servidorWeb.codigoDeRespuesta(pedido1)
        respuesta1.codigo.shouldBe(CodigoHttp.OK)
      }

      it("501 Not Implemented"){
        val repuesta2 = servidorWeb.codigoDeRespuesta(pedido2)
        repuesta2.codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
      }

      it("404 Not Found"){
        val respuesta3 = servidorWeb.codigoDeRespuesta(pedido3)
        respuesta3.codigo.shouldBe(CodigoHttp.NOT_FOUND)
      }

    }

    describe("Test modulos") {

      describe("trabaja con extensiones") {

        it("DOC") {
          modulo1.puedeTrabajarCon("https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.doc")
            .shouldBe(true)
        }
        it("GIF") {
          modulo1.puedeTrabajarCon("https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.gif")
            .shouldBe(true)
        }
        it("JPG") {
          modulo1.puedeTrabajarCon("https://Unahur.jpg").shouldBe(true)
        }
        it("GITHUB") {
          modulo1.puedeTrabajarCon("https://Programacion.github").shouldBe(false)
        }
        it("PNG") {
          modulo1.puedeTrabajarCon("https://Programacion.png").shouldBe(false)
        }
      }

      describe("asd"){

      }
    }
    }
})
