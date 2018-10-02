package org.deer.clouder.api.message.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;


class NodeReadyTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
    }

    @TestFactory
    Stream<DynamicTest> testSerialize() {
        final Iterator<NodeReady> testData = Arrays.asList(
            new NodeReady("node", "time"),
            new NodeReady("node", null),
            new NodeReady(null, "time"),
            new NodeReady(null, null))
            .iterator();

        return DynamicTest.stream(testData, NodeReady::toString, data -> {
            final ObjectWriter writer = objectMapper.writer();
            System.out.println(writer.writeValueAsString(data));
            final NodeReady deserialized = objectMapper.readerFor(NodeReady.class)
                .readValue(writer.writeValueAsBytes(data));

            assertEquals(data.nodeName(), deserialized.nodeName());
            assertEquals(data.createdAt(), deserialized.createdAt());
        });
    }
}