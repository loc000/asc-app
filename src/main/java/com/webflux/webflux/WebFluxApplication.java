package com.webflux.webflux;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxApplication {

	public static void main(String[] args) {
		EmbeddedChannel channel = new EmbeddedChannel();
		for (int i = 0; i < 1000000; i++) {
			GenericFutureListener<Future<Object>> listener = new NonEmptyListener();
			channel.closeFuture().addListener(listener);
			channel.closeFuture().removeListener(listener); // not removed if netty instrumentation is on
		}
		try {
			channel.close().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SpringApplication.run(WebFluxApplication.class, args);
	}
}
