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
