package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val pedido1 = Pedido("196.023.012","http://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())
    val pedido2 = Pedido("196.023.012","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())
    val pedido3 = Pedido("","https://github.com/obj2-unahur-2021s1/servidor-web-sag-schon-sasuke",LocalDateTime.now())

    describe("Test CodigoHttp de pedido"){

      it(" Devuelve 200, si el protocolo es el correcto"){
        pedido1.codigoDeRespuesta().shouldBe(CodigoHttp.OK)
      }

      it("501, si no lo es"){
        pedido2.codigoDeRespuesta().shouldBe(CodigoHttp.NOT_IMPLEMENTED)
      }

      it("Error 404, no la encontró"){
        pedido3.codigoDeRespuesta().shouldBe(CodigoHttp.NOT_FOUND)
      }

    }

  }
})
