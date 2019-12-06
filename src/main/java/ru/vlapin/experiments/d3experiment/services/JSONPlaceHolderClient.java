package ru.vlapin.experiments.d3experiment.services;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlapin.experiments.d3experiment.model.Post;

@FeignClient(
    name = "jsonplaceholder",
    url = "https://jsonplaceholder.typicode.com/")
@RequestMapping("posts")
public interface JSONPlaceHolderClient {

  @GetMapping
  List<Post> getPosts();

  @GetMapping(path = "{postId}", produces = APPLICATION_JSON_VALUE)
  Post getPostById(@PathVariable Long postId);
}
