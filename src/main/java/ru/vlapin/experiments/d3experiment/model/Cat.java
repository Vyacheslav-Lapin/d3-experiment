package ru.vlapin.experiments.d3experiment.model;

//import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
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

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Cat {

  @Id
  @Exclude
  @GeneratedValue
  @ApiModelProperty(hidden = true)
  @Column(updatable = false, nullable = false)
  Long id;

  @Version
  int version;

  @NonNull
  @ApiModelProperty(required = true)
  String name;

}
