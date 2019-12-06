package ru.vlapin.experiments.d3experiment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@Entity
@Component
@NoArgsConstructor
@RequiredArgsConstructor
public class Dog {

  @Id
  @Exclude
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  Long id;

  @Version
  @JsonIgnore
  int version;

  @NonNull
  String name;

}
