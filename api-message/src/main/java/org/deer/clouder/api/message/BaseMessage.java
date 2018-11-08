package org.deer.clouder.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Objects;

public abstract class BaseMessage {

  @JsonProperty
  private final String createdAt;

  @JsonProperty
  private final String nodeName;

  protected BaseMessage(final String createdAt, String nodeName) {
    this.createdAt = createdAt;
    this.nodeName = nodeName;
  }

  protected BaseMessage() {
    this.createdAt = LocalDateTime.now().atZone(ZoneId.of("GMT+0")).toString();
    try {
      this.nodeName = createNodeName();
    } catch (SocketException | UnknownHostException e) {
      throw new IllegalStateException(e);
    }
  }

  public String createdAt() {
    return createdAt;
  }

  public String nodeName() {
    return nodeName;
  }

  @Override
  public String toString() {
    return "BaseMessage{" +
        "createdAt='" + createdAt + '\'' +
        "nodeName='" + nodeName + "'}";
  }

  private static String createNodeName() throws SocketException, UnknownHostException {

      return Collections.list(NetworkInterface.getNetworkInterfaces())
          .stream()
          .map(networkInterface -> {
            try {
              return networkInterface.getHardwareAddress();
            } catch (SocketException e) {
              return null;
            }
          })
          .filter(Objects::nonNull)
          .map(mac -> {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
        }
        return sb.toString();
      }).findFirst()
          .orElse("N/A");

  }
}
