package ru.vlapin.experiments.d3experiment.services;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vlapin.experiments.d3experiment.model.Cat;

@FeignClient(
    name = "cats"
    , url = "http://localhost:8080"
//    , path = "/api/cats"
)
public interface CatsClient {

  @GetMapping(path = "/api/cats", produces = HAL_JSON_UTF8_VALUE)
//  Resources<Cat> getCats();
  Resources<Resource<Cat>> getCats();

  @GetMapping(path = "/api/cats/{catId}", produces = HAL_JSON_UTF8_VALUE)
  Resource<Cat> getCatById(@PathVariable Long catId);
}
