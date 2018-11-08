package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.Iterator;
import java.util.stream.Stream;
import org.deer.clouder.api.message.BaseMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

abstract class MessageSerializationTest<T extends BaseMessage> {

  private static ObjectMapper objectMapper;

  private final Class<T> messageType;

  MessageSerializationTest(Class<T> messageType) {
    this.messageType = messageType;
  }

  @BeforeAll
  static void init() {
    objectMapper = new ObjectMapper();
  }

  abstract Iterator<T> testData();

  abstract void assertSame(final T original, final T result);

  @TestFactory
  Stream<DynamicTest> testSerialize() {

    return DynamicTest.stream(testData(), T::toString, data -> {
      final ObjectWriter writer = objectMapper.writer();
      System.out.println(writer.writeValueAsString(data));
      final T deserialized = objectMapper.readerFor(messageType)
          .readValue(writer.writeValueAsBytes(data));

      assertSame(data, deserialized);
    });
  }
}
