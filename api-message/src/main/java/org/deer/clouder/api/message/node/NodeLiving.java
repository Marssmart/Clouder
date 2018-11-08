package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.deer.clouder.api.message.BaseMessage;

public class NodeLiving extends BaseMessage {


  @JsonCreator
  NodeLiving(@JsonProperty("nodeName") final String nodeName,
      @JsonProperty("createdAt") final String createdAt) {
    super(createdAt, nodeName);
  }

  public NodeLiving() {
  }

  @Override
  public String toString() {
    return "NodeLiving{} extends " + super.toString();
  }
}
