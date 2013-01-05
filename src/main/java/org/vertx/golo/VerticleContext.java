package org.vertx.golo;

import org.vertx.golo.deploy.impl.golo.GoloVerticle;
import org.vertx.java.core.Vertx;
import org.vertx.java.deploy.Container;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class VerticleContext {

  /** . */
  private final GoloVerticle verticle;

  public VerticleContext(GoloVerticle verticle) {
    this.verticle = verticle;
  }

  public static Vertx vertx() {
    return GoloVerticle.getCurrent().verticle.getVertx();
  }

  public static Container container() {
    return GoloVerticle.getCurrent().verticle.getContainer();
  }
}
