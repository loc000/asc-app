package com.webflux.webflux;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NonEmptyListener implements GenericFutureListener<Future<Object>> {

    private final byte[] bytes = new byte[1024];

    @Override
    public void operationComplete(Future<Object> objectFuture)  {
        System.out.println("channel closed");
    }
}
