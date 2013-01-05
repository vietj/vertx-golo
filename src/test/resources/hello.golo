module vertx.Hello

import org.vertx.golo.VerticleContext

function start = {

  let handler = |req| {
    req: response(): headers(): put("Content-Type", "text/html; charset=UTF-8")
    req: response(): send("<html><body><h1>Hello from vert.x!</h1></body></html>")
  }

  let server = vertx():
    createHttpServer():
    requestHandler(asInterfaceInstance(org.vertx.java.core.Handler.class, handler)):
    listen(8080)

}