module vertx.Hello

import org.vertx.java.core
import org.vertx.golo.VerticleContext

local function adapt = |h| -> asInterfaceInstance(Handler.class, h)

function start = {

  let handler = |req| {
    req: response(): headers(): put("Content-Type", "text/html; charset=UTF-8")
    req: response(): end("<html><body><h1>GoloGolo from vert.x!</h1></body></html>")
  }

  let server = vertx():
    createHttpServer():
    requestHandler(adapt(handler)): 
    listen(8080)
}
