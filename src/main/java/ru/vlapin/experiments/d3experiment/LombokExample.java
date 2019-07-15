package ru.vlapin.experiments.d3experiment;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Cleanup;
import lombok.Singular;
import lombok.SneakyThrows;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Wither;
import org.jetbrains.annotations.Contract;

@Wither
@FieldNameConstants(asEnum = true)
@Builder(toBuilder = true)
public class LombokExample {

  int x;

  @Default
  String y = "Мама мыла раму!";

  @Singular
  List<String> strings;

  public static void main(String... __) {

//    System.out.println(Fields.);

//    LombokExample lombokExample = LombokExample.builder()
//                                      .x(56)
//                                      .y("jhgsdjhsfdgsfdg")
//                                      .strings(Arrays.asList("sdfg", "sdg", "sfg"))
//                                      .string("jhgsdfg")
//                                      .string("kjghsdf")
//                                      .build();

    //...

    //LombokExample lombokExample1 = lombokExample.toBuilder().x(58).build();

//    LombokExample lombokExample2 = lombokExample.withX(234324);
  }

  @SneakyThrows
  public void method () {

    @Cleanup InputStream inputStream1 = LombokExample.class
                                            .getResourceAsStream("/gfd");


  }


}
