module vertx.Hello

import java.util
import org.vertx.java.core
import org.vertx.golo.VerticleContext

local function adapt = |h| -> asInterfaceInstance(Handler.class, h)

function start = {

  let handler = |req| {
    req: 
      response(): 
      headers(): 
      put("Content-Type", "text/html; charset=UTF-8")
    req: 
      response(): 
      end("<html><body><h1>Golo from vert.x!</h1><p>" + Date() + "</p></body></html>")
  }

  let server = vertx():
    createHttpServer():
    requestHandler(handler: to(Handler.class)):
    listen(8080)
}
