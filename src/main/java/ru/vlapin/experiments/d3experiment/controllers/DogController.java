package ru.vlapin.experiments.d3experiment.controllers;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.d3experiment.dao.DogRepository;

@Slf4j
@RestController
@RequestMapping("dog")
@RequiredArgsConstructor
public class DogController {

  DogRepository dogRepository;

//  @NotNull
//  @SneakyThrows
//  @GetMapping("{id}")
//  @ResponseBody
//  public Dog getDogById(@PathVariable @NotNull long id) {
//    Dog dog = dogRepository.getOne(id);
//    return dog;
//  }

  @PostConstruct
  @SuppressWarnings("unused")
  private void init() {
//    Dog zhuchka = new Dog("Zhuchka");
//    dogRepository.save(zhuchka);
//    log.info("Dog with id {} and name {} saved", zhuchka.getId(), zhuchka.getName());
  }
}
