package ru.vlapin.experiments.d3experiment.services;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vlapin.experiments.d3experiment.model.Post;

@FeignClient(
    name = "jsonplaceholder",
    url = "https://jsonplaceholder.typicode.com/")
public interface JSONPlaceHolderClient {

  @GetMapping("/posts")
  List<Post> getPosts();

  @GetMapping(path = "/posts/{postId}", produces = "application/json")
  Post getPostById(@PathVariable("postId") Long postId);
}
