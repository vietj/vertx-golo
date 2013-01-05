package org.vertx.golo.deploy.impl.golo;

import org.vertx.java.deploy.Verticle;
import org.vertx.java.deploy.VerticleFactory;
import org.vertx.java.deploy.impl.VerticleManager;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class GoloVerticleFactory implements VerticleFactory {

  /** . */
  private VerticleManager manager;

  public void init(VerticleManager manager) {
    this.manager = manager;
  }

  public Verticle createVerticle(String main, ClassLoader parentCL) throws Exception {
    throw new UnsupportedOperationException();
  }

  public void reportException(Throwable t) {

  }
}
