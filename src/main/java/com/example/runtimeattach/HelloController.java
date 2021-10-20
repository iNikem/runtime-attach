package com.example.runtimeattach;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.api.tracer.ServerSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("/hello")
  public String hello() {
    Span span = ServerSpan.fromContextOrNull(Context.current());
    if (span != null) {
      log.info("Found server span");
      span.setAttribute("myCustomAttribute", "Hola!");
    }
    return "Hello";
  }
}
