package ru.vlapin.experiments.d3experiment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vlapin.experiments.d3experiment.model.Dog;

@RepositoryRestResource
public interface DogRepository extends JpaRepository<Dog, Long> {
}
