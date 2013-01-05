package org.vertx.golo;

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
  public void testFoo() {

  }

}
