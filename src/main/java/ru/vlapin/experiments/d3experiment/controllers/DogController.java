package ru.vlapin.experiments.d3experiment.controllers;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.d3experiment.dao.DogRepository;
import ru.vlapin.experiments.d3experiment.model.Dog;

@Slf4j
@RestController
@RequestMapping(path = "dog")
@RequiredArgsConstructor
public class DogController {

  DogRepository dogRepository;

//  Dog dog;

  @NotNull
  @SneakyThrows
  @GetMapping("{id}")
//  @ResponseBody
  public Dog getDogById(@PathVariable long id) {
    Dog dog = dogRepository.findById(id).get();
//    return new ObjectMapper().writeValueAsString(dog);
    return dog;
  }

  @PostConstruct
  @SuppressWarnings("unused")
  private void init() {
    Dog zhuchka = new Dog("Zhuchka");
    dogRepository.save(zhuchka);
    log.info("Dog with id {} and name {} saved", zhuchka.getId(), zhuchka.getName());
  }
}
