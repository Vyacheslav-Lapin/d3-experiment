package ru.vlapin.experiments.d3experiment.model;

import lombok.Data;

@Data
public class Post {

  Long id;
  Long userId;
  String title;
  String body;
}
