package ru.vlapin.experiments.d3experiment.services;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vlapin.experiments.d3experiment.model.Cat;

@FeignClient(
    name = "cats"
//    , url = "http://localhost"
    , url = "http://127.0.0.1"
    , path = "/cats"
)
//@RequestMapping("cats")
public interface CatsClient {

  @GetMapping(produces = HAL_JSON_VALUE)
  CollectionModel<EntityModel<Cat>> getCats();

  @GetMapping(path = "{catId}", produces = HAL_JSON_VALUE)
  EntityModel<Cat> getCatById(@PathVariable Long catId);
}
