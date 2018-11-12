package org.deer.clouder.module.events.service;

import static java.lang.System.exit;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.deer.clouder.api.message.node.FileSystemElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = FileSystemService.class)
@ExtendWith(SpringExtension.class)
class FileSystemServiceTest {

  @Autowired
  private FileSystemService fileSystemService;
  private ScheduledExecutorService scheduler;
  private ExecutorService executor;

  @BeforeEach
  void init() {
    scheduler = Executors.newSingleThreadScheduledExecutor();
    executor = Executors.newCachedThreadPool();
  }

  @AfterEach
  void close() {
    scheduler.shutdown();
    executor.shutdown();
  }

  @Test
  void getAllRoots() {
    Queue<FileSystemElement> stack = new LinkedBlockingDeque<>(fileSystemService.getAllRoots());

    AtomicInteger measurementCounter = new AtomicInteger();
    scheduler
        .scheduleAtFixedRate(() -> {
          int size = stack.size();
          out.println(
              size + " paths discovered at rate " + (size / (measurementCounter.incrementAndGet() * 3)) +"/s");
        }, 3, 3, TimeUnit.SECONDS);

    AtomicInteger activeTaskCounter = new AtomicInteger();

    while (activeTaskCounter.get() != 0) {

      if (activeTaskCounter.get() > 100) {
        continue;
      }

      activeTaskCounter.incrementAndGet();

      CompletableFuture.runAsync(() -> {
        Optional.ofNullable(stack.poll())
            .ifPresent(next-> {
              next.children().forEach(child -> {
                assertFalse(stack.contains(child), () -> child + " already discovered");
                stack.add(child);
              });

              activeTaskCounter.decrementAndGet();
            });
      },executor);
    }

    assertEquals(new HashSet<>(stack).size(), stack.size(), "Duplicates found");
  }

}