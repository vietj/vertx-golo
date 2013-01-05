package org.vertx.golo.deploy.impl.golo;

import org.vertx.golo.VerticleContext;
import org.vertx.java.deploy.Verticle;

import java.lang.reflect.Method;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class GoloVerticle extends Verticle {

  public static VerticleContext getCurrent() {
    return current.get();
  }

  /** . */
  static final ThreadLocal<VerticleContext> current = new ThreadLocal<VerticleContext>();

  /** . */
  private final Class<?> clazz;

  /** . */
  private final VerticleContext context;

  GoloVerticle(Class<?> clazz) {
    this.clazz = clazz;
    this.context = new VerticleContext(this);
  }

  @Override
  public void start() throws Exception {
    Method m = clazz.getMethod("start");
    current.set(context);
    try {
      m.invoke(null);
    }
    finally {
      current.remove();
    }
  }

  @Override
  public void stop() throws Exception {
  }
}


