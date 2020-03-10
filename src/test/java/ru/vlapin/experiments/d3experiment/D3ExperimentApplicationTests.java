package ru.vlapin.experiments.d3experiment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import ru.vlapin.experiments.d3experiment.model.Cat;
import ru.vlapin.experiments.d3experiment.model.Post;
import ru.vlapin.experiments.d3experiment.services.CatsClient;
import ru.vlapin.experiments.d3experiment.services.JSONPlaceHolderClient;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class D3ExperimentApplicationTests {

  MockMvc mockMvc;

  JSONPlaceHolderClient client;

  CatsClient catsClient;

  @Test
  @DisplayName("Context loaded")
  void contextLoadedTest() {
  }

  @Test
  @SneakyThrows
  @DisplayName("Call cats via MockMVC with JSONPath parsing")
  void callCatsViaMockMVCParseViaJsonPath() {
    mockMvc.perform(MockMvcRequestBuilders.get("/cats"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(HAL_JSON_VALUE))
        .andExpect(mvcResult -> assertEquals(3,
            JsonPath.parse(mvcResult.getResponse().getContentAsString())
                .<Integer>read("$.page.totalElements").intValue()));
  }

  @Test
  @DisplayName("Call cat with id via Feign")
  void callCatsWithIdViaFeign() {
    log.info(
        Objects.requireNonNull(
            catsClient.getCatById(1L).getContent())
            .toString());
  }

  @Test
  @DisplayName("Call all cats via Feign")
  void callCatsViaFeign() {
    CollectionModel<EntityModel<Cat>> cats = catsClient.getCats();
    StreamSupport.stream(cats.spliterator(), false)
        .map(EntityModel::getContent)
        .filter(Objects::nonNull)
        .map(Cat::toString)
        .forEach(log::info);
  }

  @SneakyThrows
  @Test
  @DisplayName("Call other REST API via RestTemplate")
  void callOtherRESTAPIViaRestTemplateTest() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts/{0}";
    Post post = restTemplate.getForObject(url, Post.class, 59);

    assert post != null;
    assertThat(post.getId())
        .isEqualTo(59L);
  }

  @Test
  @DisplayName("Call other REST API via Feign client")
  void posts() {

    //when
    List<Post> posts = client.getPosts();
    Post post59 = client.getPostById(59L);

    //then
    assertThat(posts)
        .hasSize(100)
        .contains(post59);
  }
}
