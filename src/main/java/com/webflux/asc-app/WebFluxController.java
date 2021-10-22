package com.webflux.webflux;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebFluxController {

    private final Random random = new Random();

    @GetMapping
    public String getRandom() {
        int count = random.nextInt(1000);

        return "Get sum " + count;
    }
}
