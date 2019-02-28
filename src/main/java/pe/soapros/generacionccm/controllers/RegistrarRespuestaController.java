package pe.soapros.generacionccm.controllers;

import java.util.Random;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;


@Controller
public class RegistrarRespuestaController {
    
    private final static Logger LOGGER = Logger.getLogger("RegistrarRespuestaController");

    
    @RequestMapping(method = RequestMethod.POST, value="/registrar/json")

	@ResponseBody
	public Respuesta registerStudent(@RequestBody Solicitud solicitud) {
		//System.out.println("In registerStudent");
                
                Respuesta respuesta = new Respuesta();
                respuesta.setOrigen(solicitud.getOrigen());
                
                Random rand = new Random();
                
                // Generate random integers in range 0 to 999 
                int min = 1;
                int rand1 = rand.nextInt()+min; 
                
                String base64 =
                        "data:application/pdf;base64,JVBERi0xLjQKJeLjz9MKNCAwIG9iaiA8PC9MZW5ndGggOTgwL0"
                        + "ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicrZhPb9s2FMDv/hTvMiABNoakKFHajbHpVQUtep"
                        + "QcIFl2cGy11RBbhe11n3GHfZSeettppNw0DjLKBSgYsEyJ1I/v8f01hbej62pEANuP+04ZhWozupo"
                        + "SIPbXu9HFZfXHSJ5OwbDanC7KGMRRjNjTutQtw93D3fvRxUQqEKb6Z7xQGqIIJhKUACVvwT3JZ/OF"
                        + "LCsNwt01sqiEA2J4/z/Q33631/WI2k37t8MIJFmE4uN2KBD8cj+mXjUPn2Fd/zwAKM78oHxdbw/Nu"
                        + "2a1XLewarfhuDhJ/Tj9sK93n5ar5ssQJHugfsG2q2bfDqA9SvwQtYT9n5tlIIXGKTDaYw1Ve1g+wsd"
                        + "2Bx/abbtb7pp2H2oYHRTzPhM81Ft3UHB/EapGHAPPMpRSLwwt0BgFYqIYA+dpH0aO82sNc23gjS60E"
                        + "SbXJUglx5XRRT7WwfZiBU0o4plvB8WuDXZpxiDFGDHugxDMEk5plqVpKCtJOoH8Kv0pWBqKEqe1qEc"
                        + "giTEJ5XB2BkJ4qP1l9lziPjEKk/+6kHCTKyUKaQTIG6kWd+FcnhBvbhsLhUAt8hKUnss7mN0JBJLYU"
                        + "SUREAYCvUFQWl+oBIy1so4gYG70DbKjoqyQy4FPWzxlxhThztBfsJTQr1bD1/vHS6C4BHPgEfPKO4A"
                        + "2e95eSfX31MaO4PibHDMz+xaAs5eg7b+bejdEAsPMH+TnNqfYrL+qPx5aW22EngxPICY96T+YQHl0j"
                        + "vA4QGKMOD2XjYvaaqwr05p1cEgnGHf1oM/oSt155FSWZa4LoUqYS7OAEonwrElofDRED9ssxqEeRVk"
                        + "vgWJ7pDZyxjQJJGVZV6/5QDCx2X6WK7gFjK+c0kutZBmswKhzMu/hSXOTj12hMTe2ixBuE7KAqbw20"
                        + "migmGQwF6Y74VtbiWgw+UyEKt3typXivl2F5m0e9zZUoQ0SsbWcc3SvzYSaCsmSXsC0ftiFx1/q+s"
                        + "4+Mezhh3oX56fxiryKV/RHbKtFjAfgnDQMrzn3F45yfzkE57kbCpQHIxve/rLz3n59g/nl6XHECEpT"
                        + "SEkKm1GcZIhE3eBxVPYvo26am73pBpym7j1nl3U0TrNnmhucWWZnPtPc4IT2nfV1ZFXal1rCI2BEeX"
                        + "8v+cP9JeQmNEtGtlZlOPFjgrsd3F9cTOvVh6Utk6DeNPtB/sJgWY8433CfXCO+aertITQeEdf1Z9wf"
                        + "j9IrTK8GiEqu8GTYL9uk3TSr5rFxfzvVoYok1JZPkb/A6DqgUhSVKCbSQGG07XvSBPLCticM21bIJt"
                        + "zj1yw3Yqq0+U6f+A9oXEC2CmVuZHN0cmVhbQplbmRvYmoKMSAwIG9iajw8L1BhcmVudCA1IDAgUi9D"
                        + "b250ZW50cyA0IDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvUHJvY1NldCBbL1BERiAvVGV4dCAvSW"
                        + "1hZ2VCIC9JbWFnZUMgL0ltYWdlSV0vRm9udDw8L0YxIDIgMCBSL0YyIDMgMCBSPj4+Pi9NZWRpYUJve"
                        + "FswIDAgNTk1IDg0Ml0+PgplbmRvYmoKNiAwIG9ialsxIDAgUi9YWVogMCA4NTQgMF0KZW5kb2JqCjI"
                        + "gMCBvYmo8PC9CYXNlRm9udC9IZWx2ZXRpY2EvVHlwZS9Gb250L0VuY29kaW5nL1dpbkFuc2lFbmNvZ"
                        + "GluZy9TdWJ0eXBlL1R5cGUxPj4KZW5kb2JqCjMgMCBvYmo8PC9CYXNlRm9udC9IZWx2ZXRpY2EtQm9"
                        + "sZC9UeXBlL0ZvbnQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nL1N1YnR5cGUvVHlwZTE+PgplbmRv"
                        + "YmoKNSAwIG9iajw8L1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago3IDAgb2"
                        + "JqPDwvTmFtZXNbKEpSX1BBR0VfQU5DSE9SXzBfMSkgNiAwIFJdPj4KZW5kb2JqCjggMCBvYmo8PC9E"
                        + "ZXN0cyA3IDAgUj4+CmVuZG9iago5IDAgb2JqPDwvTmFtZXMgOCAwIFIvVHlwZS9DYXRhbG9nL1BhZ2"
                        + "VzIDUgMCBSPj4KZW5kb2JqCjEwIDAgb2JqPDwvQ3JlYXRvcihTVU5BVCBKYXZhIEdlbkRvYyBkZXJl"
                        + "Y2hvcyByZXNlcnZhZG9zKS9Qcm9kdWNlcihpVGV4dCAxLjQuNiBcKGJ5IGxvd2FnaWUuY29tXCkpL0"
                        + "1vZERhdGUoRDoyMDE5MDIyNjEwMTgxNC0wNScwMCcpL0F1dGhvcihzdW5hdC5nb2IucGUpL0NyZWF0a"
                        + "W9uRGF0ZShEOjIwMTkwMjI2MTAxODE0LTA1JzAwJyk+PgplbmRvYmoKeHJlZgowIDExCjAwMDAwMDAw"
                        + "MDAgNjU1MzUgZiAKMDAwMDAwMTA2MiAwMDAwMCBuIAowMDAwMDAxMjYxIDAwMDAwIG4gCjAwMDAwMDE"
                        + "zNDggMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAxNDQwIDAwMDAwIG4gCjAwMDAwM"
                        + "DEyMjcgMDAwMDAgbiAKMDAwMDAwMTQ5MCAwMDAwMCBuIAowMDAwMDAxNTQzIDAwMDAwIG4gCjAwMDA"
                        + "wMDE1NzQgMDAwMDAgbiAKMDAwMDAwMTYzMCAwMDAwMCBuIAp0cmFpbGVyCjw8L1Jvb3QgOSAwIFIvSU"
                        + "QgWzw1Yzc0YWY3Mjg4ZmM4Nzc2YzNjYTlmZGE4NGRiYzU3Yz48OTA5MzJlYTRmOWVmYmMyNjA4OGIxN"
                        + "mNhNTM2MzdmNzA+XS9JbmZvIDEwIDAgUi9TaXplIDExPj4Kc3RhcnR4cmVmCjE4MzAKJSVFT0YK";
                
                respuesta.setCodigoTransaccion("COD"+rand1);
                respuesta.setPdf(base64);
		return respuesta;
                
	}
    
    
}
