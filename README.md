Golo language integration for Vert.x server

# Installation

We suppose that Vert.x 2.0.0 is installed already in $VERTX_HOME

1. Clone or download this build
1. Build project with Maven : `mvn package`
1. Copy to $VERTX_HOME/lib the jars:
    1. the produced jar: `vertx-golo-1.0-SNAPSHOT.jar`
    1. [golo jar](http://search.maven.org/remotecontent?filepath=org/golo-lang/golo/0-preview1/golo-0-preview1.jar): `golo-0-preview1.jar`
    1. [asm jar](http://search.maven.org/remotecontent?filepath=org/ow2/asm/asm/4.1/asm-4.1.jar): `asm-4.1.jar`
1. Declare Golo as a supported language:
    1. Open $VERTX_HOME/conf/langs.properties
    1. add the line `golo=org.vertx.golo.deploy.impl.golo.GoloVerticleFactory`

# Usage

Create a file named `hello.golo` with the content:

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

Run it with Vert.x

    vertx run hello.golo

Open your browser on [localhost](http://localhost:8080).

More information on:

1. Golo on [Golo site](http://golo-lang.org)
1. Vert.x on [Vert.x site](http://vertx.io)
