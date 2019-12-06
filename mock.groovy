//$ spring run mock.groovy
@RestController
class ThisWillActuallyRun {

  @GetMapping("api/hello")
  String home() {
    "Hello World!"
  }
}
