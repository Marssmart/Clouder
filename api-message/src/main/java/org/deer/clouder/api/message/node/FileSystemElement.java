package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.Set;

public class FileSystemElement {

  @JsonProperty
  private final String path;

  @JsonProperty
  private final Set<FileSystemElement> children;

  @JsonCreator
  public FileSystemElement(@JsonProperty("path") final String path,
      @JsonProperty("children") final Set<FileSystemElement> children) {
    this.path = path;
    this.children = children;
  }

  public String path() {
    return path;
  }

  public Set<FileSystemElement> children() {
    return children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileSystemElement that = (FileSystemElement) o;
    return Objects.equals(path, that.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path);
  }

  @Override
  public String toString() {
    return "FileSystemElement{" +
        "path='" + path + '\'' +
        ", children=" + children +
        '}';
  }
}
