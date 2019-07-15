package ru.vlapin.experiments.d3experiment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

import com.jayway.jsonpath.JsonPath;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.vlapin.experiments.d3experiment.model.Cat;
import ru.vlapin.experiments.d3experiment.services.CatsClient;
import ru.vlapin.experiments.d3experiment.services.JSONPlaceHolderClient;

@Slf4j
@SpringBootTest(classes = D3ExperimentApplication.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@WithMockUser(authorities = "ADMIN")
class D3ExperimentApplicationTests {

  MockMvc mockMvc;

  JSONPlaceHolderClient client;

  CatsClient catsClient;

  @Test
  @SneakyThrows
  @DisplayName("Call cats via MockMVC with JSONPath parsing")
  void callCatsViaMockMVCParseViaJsonPath() {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/cats"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(HAL_JSON_UTF8_VALUE))
        .andExpect(mvcResult -> assertEquals(3,
            JsonPath.parse(mvcResult.getResponse().getContentAsString())
                .<Integer>read("$.page.totalElements").intValue()));
  }

  @Test
  @Disabled // TODO: 2019-06-02
  @DisplayName("Call cats with id via Feign")
  void callCatsWithIdViaFeign() {
    log.info(catsClient.getCatById(2L).getContent().toString());
  }

  @Test
  @Disabled // TODO: 2019-06-02
  @DisplayName("Call all cats via Feign")
  void callCatsViaFeign() {
//    Resources<Cat> cats = catsClient.getCats();
    Resources<Resource<Cat>> cats = catsClient.getCats();
    StreamSupport.stream(cats.spliterator(), false)
        .map(Resource::getContent)
        .map(Cat::toString)
        .forEach(log::info);
  }

  @Test
  @DisplayName("Call other REST API via Feign client")
  void posts() {
    assertThat(client.getPosts())
        .hasSize(100)
        .contains(client.getPostById(59L));
  }
}
