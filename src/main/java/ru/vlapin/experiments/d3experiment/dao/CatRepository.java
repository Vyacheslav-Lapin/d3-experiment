package ru.vlapin.experiments.d3experiment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vlapin.experiments.d3experiment.model.Cat;

@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, Long> {
}
