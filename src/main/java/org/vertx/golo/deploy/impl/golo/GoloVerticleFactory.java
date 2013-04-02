package org.vertx.golo.deploy.impl.golo;

import fr.insalyon.citi.golo.compiler.GoloClassLoader;
import org.vertx.java.deploy.Verticle;
import org.vertx.java.deploy.impl.VerticleFactory;
import org.vertx.java.deploy.impl.VerticleManager;

import java.io.InputStream;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class GoloVerticleFactory implements VerticleFactory {

  /** . */
  private VerticleManager manager;

  public void init(VerticleManager manager) {
    this.manager = manager;
  }

  public Verticle createVerticle(String main, ClassLoader parentCL) throws Exception {
    InputStream in = parentCL.getResourceAsStream(main);
    if (in == null) {
      throw new Exception("Could not locate golo source " + main);
    }
    GoloClassLoader loader = new GoloClassLoader(parentCL);
    Class<?> clazz = loader.load(main, in);
    return new GoloVerticle(clazz);
  }

  public void reportException(Throwable t) {
    t.printStackTrace();
  }
}
