package org.deer.clouder.module.main.dto;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Node {

  @Id
  private String name;
  private String lastUpdate;
  private List<Prop> properties;

  public Node(){
  }

  public Node(String name){
    this.name=name;
  }

  public Node(String name, String lastUpdate,
      List<Prop> properties) {
    this.name = name;
    this.lastUpdate = lastUpdate;
    this.properties = properties;
  }

  public String getName() {
    return name;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public List<Prop> getProperties() {
    return properties;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Node setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  public Node setProperties(List<Prop> properties) {
    this.properties = properties;
    return this;
  }
}
