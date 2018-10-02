package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.deer.clouder.api.message.BaseMessage;

public class NodeReady extends BaseMessage {

    @JsonProperty("nodeName")
    private final String nodeName;

    @JsonCreator
    NodeReady(@JsonProperty("nodeName") final String nodeName, @JsonProperty("createAt") final String createdAt) {
        super(createdAt);
        this.nodeName = nodeName;
    }

    public NodeReady(final String nodeName) {
        this.nodeName = nodeName;
    }

    public String nodeName() {
        return nodeName;
    }

    @Override
    public String toString() {
        return "NodeReady{" +
            "nodeName='" + nodeName + '\'' +
            "} extends " + super.toString();
    }
}
