package org.deer.clouder.module.events.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.deer.clouder.api.message.node.SystemProperty;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

  public Set<SystemProperty> getAllProperties() {
    return System.getProperties()
        .entrySet()
        .stream()
        .map(entry ->
            new SystemProperty(entry.getKey().toString(),
                entry.getValue().toString()))
        .collect(Collectors.toSet());
  }
}
