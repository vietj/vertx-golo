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

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Assert;
import org.junit.Test;
import org.vertx.java.testframework.TestBase;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class HelloTestCase extends TestBase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    startApp("hello.golo");
  }

  @Test
  public void testFoo() throws Exception {
    Response response = Request.Get("http://localhost:8080/").execute();
    String expected = "GoloGolo from vert.x!";
    String content = response.returnContent().asString();
    Assert.assertTrue("Was expecting " + content + " to contain " + expected, content.contains(expected));
  }

}
