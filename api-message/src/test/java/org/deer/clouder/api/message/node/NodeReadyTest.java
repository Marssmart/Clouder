package org.deer.clouder.api.message.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.Test;


class NodeReadyTest extends MessageSerializationTest<NodeReady> {

  NodeReadyTest() {
    super(NodeReady.class);
  }

  @Override
  Iterator<NodeReady> testData() {
    return Arrays.asList(
        new NodeReady("node", "time"),
        new NodeReady("node", null),
        new NodeReady(null, "time"),
        new NodeReady(null, null))
        .iterator();
  }

  @Override
  void assertSame(NodeReady original, NodeReady result) {
    assertEquals(original.nodeName(), result.nodeName());
    assertEquals(original.createdAt(), result.createdAt());
  }

  @Test
  void baseCreationTest() {
    NodeReady node = new NodeReady();
    System.out.println("Node " + node);
  }
}