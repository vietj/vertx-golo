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
