package ru.vlapin.experiments.d3experiment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

  @Id
  @Exclude
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  Long id;

  @Version
  @Exclude
  int version;

  @NotNull
  @NonNull
  String title;

  @NotNull
  @NonNull
  @Column(columnDefinition = "VARCHAR", length = 100)
  String author;

  @NonNull
  @Column(columnDefinition = "VARCHAR", length = 1_000)
  String blurb;

  @NonNull
  Integer pages;

}
