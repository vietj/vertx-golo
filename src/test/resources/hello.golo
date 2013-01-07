module vertx.Hello

import java.util.concurrent.atomic 
import org.vertx.java.core
import org.vertx.golo.VerticleContext

local function adapt = |h| -> asInterfaceInstance(Handler.class, h)

function start = {

  let integer = AtomicInteger(0)

  let handler = |req| {
    let stamp = integer: incrementAndGet()
    req: response(): headers(): put("Content-Type", "text/html; charset=UTF-8")
    req: response(): end("<html><body><h1>GoloGolo from vert.x!</h1><p>Call number " + stamp + "</p></body></html>")
  }

  let server = vertx():
    createHttpServer():
    requestHandler(adapt(handler)): 
    listen(8080)
}
