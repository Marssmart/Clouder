package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.deer.clouder.api.message.BaseMessage;

public class NodeLiving extends BaseMessage {

    @JsonProperty("nodeName")
    private final String nodeName;

    @JsonCreator
    NodeLiving(@JsonProperty("nodeName") final String nodeName,
        @JsonProperty("createdAt") final String createdAt) {
        super(createdAt);
        this.nodeName = nodeName;
    }

    public NodeLiving(final String nodeName) {
        this.nodeName = nodeName;
    }


    public String nodeName() {
        return nodeName;
    }

    @Override
    public String toString() {
        return "NodeLiving{" +
            "nodeName='" + nodeName + '\'' +
            "} extends " + super.toString();
    }
}
