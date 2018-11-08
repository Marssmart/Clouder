package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import org.deer.clouder.api.message.BaseMessage;

public class NodeInfo extends BaseMessage {

  @JsonProperty
  private final Set<SystemProperty> systemProperties;

  @JsonProperty
  private final Set<FileSystemElement> fileSystemElements;

  @JsonCreator
  NodeInfo(@JsonProperty("nodeName") final String nodeName,
      @JsonProperty("createdAt") final String createdAt,
      @JsonProperty("systemProperties") final Set<SystemProperty> systemProperties,
      @JsonProperty("fileSystemElements") final Set<FileSystemElement> fileSystemElements) {
    super(createdAt, nodeName);
    this.systemProperties = systemProperties;
    this.fileSystemElements = fileSystemElements;
  }

  public NodeInfo(Set<SystemProperty> systemProperties,
      Set<FileSystemElement> fileSystemElements) {
    this.systemProperties = systemProperties;
    this.fileSystemElements = fileSystemElements;
  }

  public Set<SystemProperty> systemProperties() {
    return systemProperties;
  }

  public Set<FileSystemElement> fileSystemElements() {
    return fileSystemElements;
  }

  @Override
  public String toString() {
    return "NodeInfo{" +
        "systemProperties=" + systemProperties +
        ", fileSystemElements=" + fileSystemElements +
        "} extends " + super.toString();
  }
}
