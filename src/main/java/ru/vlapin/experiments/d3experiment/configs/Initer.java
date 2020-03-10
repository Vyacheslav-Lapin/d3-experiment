package ru.vlapin.experiments.d3experiment.configs;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.vlapin.experiments.d3experiment.dao.CatRepository;
import ru.vlapin.experiments.d3experiment.model.Cat;

@Component
@RequiredArgsConstructor
public class Initer implements ApplicationRunner {

  CatRepository catRepository;

  @Override
  public void run(ApplicationArguments __) {
//    System.setProperty("java.net.preferIPv4Stack", "true");

    Stream.of("Murzik", "Barsik", "Matroskin")
        .map(Cat::new)
        .forEach(catRepository::save);
  }
}
