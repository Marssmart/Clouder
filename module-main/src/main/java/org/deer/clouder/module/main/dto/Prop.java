package org.deer.clouder.module.main.dto;

import org.springframework.data.annotation.Id;

public class Prop {

  @Id
  private String key;
  private String value;

  public Prop() {
  }

  public Prop(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
