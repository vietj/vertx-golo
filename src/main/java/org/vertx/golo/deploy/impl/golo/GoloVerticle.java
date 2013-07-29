/*
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.vertx.golo.deploy.impl.golo;

import org.vertx.golo.VerticleContext;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxException;
import org.vertx.java.platform.Container;
import org.vertx.java.platform.Verticle;

import java.lang.reflect.InvocationTargetException;
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

  GoloVerticle(final Vertx vertx, final Container container, Class<?> clazz) {
    this.clazz = clazz;
    this.context = new VerticleContext() {
      @Override
      public Vertx getVertx() {
        return vertx;
      }
      @Override
      public Container getContainer() {
        return container;
      }
    };
  }

  @Override
  public void start() {
    lifeCycle("start");
  }

  @Override
  public void stop() {
    lifeCycle("stop");
  }

  public void lifeCycle(String lifeCycle) {
    Method m;
    try {
      m = clazz.getMethod(lifeCycle);
    }
    catch (NoSuchMethodException e) {
      // No stop
      m = null;
    }
    if (m != null) {
      current.set(context);
      try {
        m.invoke(null);
      }
      catch (InvocationTargetException e) {
        throw new VertxException("Could not execute life cycle " + lifeCycle +" verticle", e.getCause());
      }
      catch (IllegalAccessException e) {
        throw new VertxException("Could not use verticle", e);
      }
      finally {
        current.remove();
      }
    }
  }
}


