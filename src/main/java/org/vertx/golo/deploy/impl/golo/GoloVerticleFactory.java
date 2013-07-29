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

import fr.insalyon.citi.golo.compiler.GoloClassLoader;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Container;
import org.vertx.java.platform.Verticle;
import org.vertx.java.platform.VerticleFactory;

import java.io.InputStream;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class GoloVerticleFactory implements VerticleFactory {

  /** . */
  private Vertx vertx;

  /** . */
  private Container container;

  /** . */
  private ClassLoader classLoader;

  public void init(Vertx vertx, Container container, ClassLoader cl) {
    this.vertx = vertx;
    this.container = container;
    this.classLoader = cl;
  }

  public Verticle createVerticle(String main) throws Exception {
    InputStream in = classLoader.getResourceAsStream(main);
    if (in == null) {
      throw new Exception("Could not locate golo source " + main);
    }
    GoloClassLoader loader = new GoloClassLoader(classLoader);
    Class<?> clazz = loader.load(main, in);
    return new GoloVerticle(vertx, container, clazz);
  }

  public void reportException(Logger logger, Throwable t) {
    logger.error("", t);
  }

  public void close() {
  }
}
