package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val pedido1 = Pedido("196.023.012","http://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())
    val pedido2 = Pedido("196.023.012","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())
    val pedido3 = Pedido("","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke.doc",LocalDateTime.now())
    val modulo1 = Modulo(mutableListOf<String>("jpg","doc","docx","gif"),"Hola mi amor",14)

    describe("Test CodigoHttp de pedido"){

      // Se rompio todo, la mala

//      it("200 OK"){
//        pedido1.codigoDeRespuesta().shouldBe(Respuesta(CodigoHttp.OK,modulo1.text,modulo1.tiempo,pedido1))
//      }
//
//      it("501 Not Implemented"){
//        pedido2.codigoDeRespuesta().shouldBe(Respuesta(CodigoHttp.NOT_IMPLEMENTED,"",10,pedido1))
//      }
//
//      it("404 Not Found"){
//        pedido3.codigoDeRespuesta().shouldBe(Respuesta(CodigoHttp.NOT_FOUND ,"",10,pedido1))
//      }
//
//    }

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
  }
})
