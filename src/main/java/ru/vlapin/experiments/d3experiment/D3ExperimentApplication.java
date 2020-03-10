package ru.vlapin.experiments.d3experiment;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = HAL)
@EnableFeignClients("ru.vlapin.experiments.d3experiment.services")
public class D3ExperimentApplication {

  public static void main(String... args) {
    SpringApplication.run(D3ExperimentApplication.class, args);
  }

}
