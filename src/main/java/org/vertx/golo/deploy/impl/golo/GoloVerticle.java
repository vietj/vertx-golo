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


