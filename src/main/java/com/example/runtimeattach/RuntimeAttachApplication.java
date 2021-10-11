package com.example.runtimeattach;

import io.opentelemetry.contrib.attach.RuntimeAttach;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuntimeAttachApplication {

  public static void main(String[] args) {
    System.setProperty("otel.service.name", "MVC Example");
    System.setProperty("otel.traces.exporter", "logging");
    System.setProperty("otel.metrics.exporter", "none");
    RuntimeAttach.attachJavaagentToCurrentJVM();
    SpringApplication.run(RuntimeAttachApplication.class, args);
  }

}
