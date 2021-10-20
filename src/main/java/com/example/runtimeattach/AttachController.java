package com.example.runtimeattach;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.contrib.attach.RuntimeAttach;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttachController {
  private static final Logger log = LoggerFactory.getLogger(AttachController.class);
  private final AtomicBoolean init = new AtomicBoolean(false);

  @GetMapping("/init")
  public String init() {
    String result = Span.current().getSpanContext().toString();
    log.info("Current {}", result);
    if (init.compareAndSet(false, true)) {
      RuntimeAttach.attachJavaagentToCurrentJVM();
    }
    return result;
  }
}
