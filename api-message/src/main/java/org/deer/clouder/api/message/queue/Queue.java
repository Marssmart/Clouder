package org.deer.clouder.api.message.queue;

public enum Queue {
  NODE_READY_QUEUE("org.deer.node.ready"),
  NODE_LIVING_QUEUE("org.deer.node.living"),
  NODE_INFO_QUEUE("org.deer.node.info");

  private final String route;

  Queue(String route) {
    this.route = route;
  }

  public String route() {
    return route;
  }
}


