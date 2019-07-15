package ru.vlapin.experiments.d3experiment.configs;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.vlapin.experiments.d3experiment.dao.CatRepository;
import ru.vlapin.experiments.d3experiment.dao.DogRepository;
import ru.vlapin.experiments.d3experiment.model.Cat;
import ru.vlapin.experiments.d3experiment.model.Dog;

@Component
@RequiredArgsConstructor
public class Initer implements ApplicationRunner {

  CatRepository catRepository;

  DogRepository dogRepository;

  @Override
  public void run(ApplicationArguments args) {
    Stream.of("Murzik", "Barsik", "Matroskin")
        .map(Cat::new)
        .forEach(catRepository::save);

    Stream.of("Zhuchka", "Sharik", "Barbos")
        .map(Dog::new)
        .forEach(dogRepository::save);

  }
}
