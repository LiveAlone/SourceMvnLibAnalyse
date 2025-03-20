package org.yqj.source.reactor.netty.doc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.resources.LoopResources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.function.BiFunction;

/**
 * @author 10126730
 * Date: 2025/3/18 20:05
 * Description:
 */
@Slf4j
public class HttpServerDemo {
    public static void main(String[] args) {
//        httpProtocol();

//        router();

//        create();

        LoopResources loop = LoopResources.create("yqj-elp", 1, 4, true);
        DisposableServer server = HttpServer.create()
                .port(8080).wiretap(true)
                .runOn(loop)
                .handle((request, response) -> {
                    log.info("current thread is: {}", Thread.currentThread().getName());
                    return response.sendString(Mono.just("hello"));
                })
                .bindNow();
        server.onDispose().block();
    }

    private static void httpProtocol() {
        // 1 http 协议 no protocol
        // curl --http2-prior-knowledge localhost:8080
        DisposableServer server = HttpServer.create()
                .port(8080)
//                .protocol(HttpProtocol.H2C)
                .wiretap(true)
                .handle((request, response) -> {
                    log.info("current thread is: {}", Thread.currentThread().getName());
                    return response.sendString(Mono.just("hello"));
                })
                .bindNow();
        server.onDispose().block();
    }

    private static void router() {
        // 1 路由
//        DisposableServer server = HttpServer.create()
//                .host("localhost").port(8080)
//                .route(routes ->
//                        routes.get("/hello",(request, response) -> response.sendString(Mono.just("Hello World!")))
//                                .post("/echo", (request, response) -> response.send(request.receive().retain()))
//                                .get("/path/{param}", (request, response) -> response.sendString(Mono.just(request.param("param"))))
//                                .ws("/ws", (wsInbound, wsOutbound) -> wsOutbound.send(wsInbound.receive().retain())))
//                        .bindNow();
//        server.onDispose().block();

        // 2 see
        // curl -H "Accept: text/event-stream" http://localhost:8080/sse
//        DisposableServer server = HttpServer.create()
//                .host("localhost").port(8080)
//                .route(routes -> routes.get("/sse", serveSse()))
//                .route(routes -> routes.file("/index.html", new File("12").toPath()))
//                .bindNow();
//        server.onDispose().block();

        // 3 consume data
//        DisposableServer server = HttpServer.create()
//                .host("localhost").port(8080)
//                .route(routes -> routes.get("/hello", (request, response) ->
//                        response.status(HttpResponseStatus.OK)
//                                .header(HttpHeaderNames.CONTENT_LENGTH, "12")
//                                .sendString(Mono.just("Hello World!"))))
//                .bindNow();
//        server.onDispose().block();
    }

    private static BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> serveSse() {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(2));
        return (request, response) ->
                response.sse().send(flux.map(HttpServerDemo::toByteBuf), b -> true);
    }
    private static ByteBuf toByteBuf(Object any) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write("data: ".getBytes(Charset.defaultCharset()));
            out.write(any.toString().getBytes(Charset.defaultCharset()));
            out.write("\n\n".getBytes(Charset.defaultCharset()));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }

    private static void create() {
        // 1 http server 创建
//        DisposableServer server = HttpServer.create()
//                .host("localhost").port(8080)
//                .bindNow();
//        server.onDispose().block();

        // 2 多服务创建
//        HttpServer httpServer = HttpServer.create();
//        DisposableServer server1 = httpServer
//                .host("localhost") //<1>
//                .port(8080)        //<2>
//                .bindNow();
//
//        DisposableServer server2 = httpServer
//                .host("0.0.0.0") //<3>
//                .port(8081)      //<4>
//                .bindNow();
//
//        Mono.when(server1.onDispose(), server2.onDispose())
//                .block();

        // 3 资源预热
        HttpServer httpServer = HttpServer.create()
                .host("localhost").port(8080)
                .handle((request, response) -> request.receive().then());
        httpServer.warmup().block();
        DisposableServer server = httpServer.bindNow();
        server.onDispose().block();
    }
}
