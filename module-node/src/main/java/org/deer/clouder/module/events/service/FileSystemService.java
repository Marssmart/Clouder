package org.deer.clouder.module.events.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.deer.clouder.api.message.node.FileSystemElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileSystemService {

  private static final Logger LOG = LoggerFactory.getLogger(FileSystemService.class);

  public Set<FileSystemElement> getAllRoots() {
    return StreamSupport.stream(FileSystems.getDefault().getRootDirectories().spliterator(), true)
        .map(Path::toAbsolutePath)
        .map(Path::toString)
        .map(FileSystemElement::new)
        .collect(Collectors.toSet());
  }
}
