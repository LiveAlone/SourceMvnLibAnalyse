package org.yqj.source.reactor.netty;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

/**
 * @author 10126730
 * Date: 2025/3/13 14:52
 * Description:
 */
@Slf4j
public class LocalHttpServer {

    public static void main(String[] args) {
        createServer();
    }

    public static void createServer() {
        // 1. create http server process
//        DisposableServer server = HttpServer.create()
//                .route(routes ->
//                        routes.get("/hello", (request, response) -> response.sendString(Mono.just("Hello World!")))
//                )
//                .host("localhost").port(8080).bindNow();
//        server.onDispose().block();

        // 2. bind now warm up
        HttpServer httpServer = HttpServer.create()
                .handle((request, response) -> request.receive().then());
        httpServer.warmup().block();
        DisposableServer server = httpServer.bindNow();
        server.onDispose().block();
    }

}
