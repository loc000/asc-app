package com.webflux.webflux;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebFluxController {

    private final Random random = new Random();

    private final EmbeddedChannel channel = new EmbeddedChannel();

    @GetMapping("random")
    public String getRandom() {
        int count = random.nextInt(200000);

        int sum = getSum(count);

        return "Get sum " + sum;
    }

    private int getSum(int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += count;

            GenericFutureListener<Future<Object>> listener = new NonEmptyListener();
            channel.closeFuture().addListener(listener);
            channel.closeFuture().removeListener(listener); // not removed if netty instrumentation is on
        }

        try {
            channel.close().await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sum;
    }
}
