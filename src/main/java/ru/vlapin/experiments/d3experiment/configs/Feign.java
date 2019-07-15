package ru.vlapin.experiments.d3experiment.configs;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableFeignClients("ru.vlapin.experiments.d3experiment.services")
@EnableHypermediaSupport(type = HAL)
public class Feign {

//    @Bean
//    public Decoder feignDecoder() {
//      return new ResponseEntityDecoder(
//          new JacksonDecoder(
//              new ObjectMapper()
//                  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                  .registerModule(new Jackson2HalModule())));
//    }
}
