package com.example.runtimeattach;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.api.tracer.ServerSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    Span span = ServerSpan.fromContextOrNull(Context.current());
    if (span != null) {
      span.setAttribute("myCustomAttribute", "Hola!");
    }
    return "Hello";
  }
}
