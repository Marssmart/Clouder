package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystemElement {

  public static final File[] NO_FILES = new File[0];

  @JsonProperty
  private final String path;

  @JsonCreator
  public FileSystemElement(@JsonProperty("path") final String path) {
    this.path = path;
  }

  public String path() {
    return path;
  }

  public Set<FileSystemElement> children() {
    return Arrays.stream(Optional.ofNullable(Paths.get(path).toFile().listFiles()).orElse(NO_FILES))
        .map(File::getAbsolutePath)
        .map(FileSystemElement::new)
        .collect(Collectors.toSet());
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
        "path='" + path + "}";
  }
}
