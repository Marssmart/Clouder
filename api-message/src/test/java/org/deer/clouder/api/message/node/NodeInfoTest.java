package org.deer.clouder.api.message.node;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

class NodeInfoTest extends MessageSerializationTest<NodeInfo> {

  NodeInfoTest() {
    super(NodeInfo.class);
  }

  @Override
  Iterator<NodeInfo> testData() {
    return Arrays.asList(
        new NodeInfo("node", "time", null, null),
        new NodeInfo("node", "time", emptySet(), emptySet()),
        new NodeInfo("node", "time", singleton(new SystemProperty("key", "value")),
            singleton(new FileSystemElement("path1",
                singleton(new FileSystemElement("path1/path2", null)))))
    ).iterator();
  }

  @Override
  void assertSame(NodeInfo original, NodeInfo result) {
    assertEquals(original.nodeName(), result.nodeName());
    assertEquals(original.createdAt(), result.createdAt());
    assertEquals(original.systemProperties(), result.systemProperties());
  }
}