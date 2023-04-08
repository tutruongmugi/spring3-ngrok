package springboot.study.spring3ngrok.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Tag(name = "Test", description = "Controller for testing api")
@RequiredArgsConstructor
public class TestController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
