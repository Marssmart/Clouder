package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.deer.clouder.api.message.BaseMessage;

public class NodeReady extends BaseMessage {


  @JsonCreator
  NodeReady(@JsonProperty("nodeName") final String nodeName,
      @JsonProperty("createAt") final String createdAt) {
    super(createdAt, nodeName);
  }

  public NodeReady() {
  }

  @Override
  public String toString() {
    return "NodeReady{} extends " + super.toString();
  }
}
