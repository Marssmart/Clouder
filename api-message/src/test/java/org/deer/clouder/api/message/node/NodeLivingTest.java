package org.deer.clouder.api.message.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

class NodeLivingTest extends MessageSerializationTest<NodeLiving> {

  NodeLivingTest() {
    super(NodeLiving.class);
  }

  @Override
  Iterator<NodeLiving> testData() {
    return Arrays.asList(
        new NodeLiving("node", "time"),
        new NodeLiving("node", null),
        new NodeLiving(null, "time"),
        new NodeLiving(null, null))
        .iterator();
  }

  @Override
  void assertSame(NodeLiving original, NodeLiving result) {
    assertEquals(original.nodeName(), result.nodeName());
    assertEquals(original.createdAt(), result.createdAt());
  }
}