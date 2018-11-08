package org.deer.clouder.api.message.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class SystemProperty {

  @JsonProperty
  private final String key;

  @JsonProperty
  private final String value;

  @JsonCreator
  public SystemProperty(@JsonProperty("key") final String key,
      @JsonProperty("value") final String value) {
    this.key = key;
    this.value = value;
  }

  public String key() {
    return key;
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemProperty that = (SystemProperty) o;
    return Objects.equals(key, that.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "SystemProperty{" +
        "key='" + key + '\'' +
        ", value='" + value + '\'' +
        '}';
  }
}
