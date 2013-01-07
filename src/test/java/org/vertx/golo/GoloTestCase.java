package org.vertx.golo;

import junit.framework.Assert;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vertx.java.test.TestVerticle;
import org.vertx.java.test.VertxConfiguration;
import org.vertx.java.test.VertxTestBase;
import org.vertx.java.test.junit.VertxJUnit4ClassRunner;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
@RunWith(VertxJUnit4ClassRunner.class)
@VertxConfiguration()
@TestVerticle(main="hello.golo")
public class GoloTestCase extends VertxTestBase {

  @Test
  public void testFoo() throws Exception {
    Response response = Request.Get("http://localhost:8080/").execute();
    String expected = "GoloGolo from vert.x!";
    String content = response.returnContent().asString();
    Assert.assertTrue("Was expecting " + content + " to contain " + expected, content.contains(expected));
  }

}
