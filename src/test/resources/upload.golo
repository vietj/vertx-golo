module vertx.Hello

import org.vertx.java.core
import org.vertx.java.core.streams.Pump
import org.vertx.golo.VerticleContext
import java.util.UUID
import java.lang.System

local function asHandler = |h| -> asInterfaceInstance(Handler.class, h)
local function asAsyncHandler = |h| -> asInterfaceInstance(AsyncResultHandler.class, h)

function start = {

  let contextualHandler = |vertx, req| {
    req:pause()
    let filename = "upload/file-" + randomUUID() + ".upload"
    let fileHandler = |asyncFile| {
      if (asyncFile:failed()) {
        println("Could not open file " + filename)
        asyncFile:exception():printStackTrace()
      } else {
        println("Created file " + filename)
        let file = asyncFile:result()
        let pump = createPump(req, file:getWriteStream())
        let start = currentTimeMillis()
        let endHandler = |void| {
          println("Received bytes from request")
          let closeHandler = |req, result| {
            if (result:failed()) {
              req: response(): statusCode(500)
              req: response(): headers(): put("Content-Type", "text/html; charset=UTF-8")
              req: response(): end("<html><body><h1>Could not upload file file</h1></body></html>")
            } else {
              req: response(): headers(): put("Content-Type", "text/html; charset=UTF-8")
              req: response(): end("<html><body><h1>Uploaded file</h1></body></html>")
            }
          }
          file:close(asAsyncHandler(closeHandler: bindAt(0, req)))
        }
        req:endHandler(asHandler(endHandler))
        pump:start()
        req:resume()
      }
    }
    vertx:fileSystem():open(filename, asAsyncHandler(fileHandler))
  }

  let handler = contextualHandler: bindAt(0, vertx())
  let server = vertx():
    createHttpServer():
    requestHandler(asHandler(handler)): 
    listen(8081)
}
