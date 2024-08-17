package first_project.demo.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiTest {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }

}
