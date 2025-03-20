package org.yqj.source.reactor.netty.doc;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpServer;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 10126730
 * Date: 2025/3/18 13:40
 * Description:
 */
@Slf4j
public class TcpServerDemo {

    public static void main(String[] args) {

//        tcpConfig();

//        lifeCycle();

//        dataSendRead();

//        createServer();

        LoopResources loop = LoopResources.create("event-loop", 1, 4, true);
        DisposableServer server = TcpServer.create()
                .port(8080)
                .runOn(loop)
                .handle((inbound, outbound) -> {
                    String resp = String.format("Hello, %s!", new Date());
                    log.info("resp is {} current thread is: {}", resp, Thread.currentThread().getName());
                    return outbound.sendString(Mono.just(resp));
                })
                .bindNow();
        server.onDispose().block();
    }

    private static void tcpConfig() {
        // 1 channel opt
//        DisposableServer server = TcpServer.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                .bindNow();
//        server.onDispose().block();

        // 2 wire log
//        DisposableServer server =
//                TcpServer.create()
//                        .host("localhost").port(8080)
//                        .wiretap("logger-name", LogLevel.INFO, AdvancedByteBufFormat.HEX_DUMP)
//                        .handle((inbound, outbound) ->
//                                inbound.receive().asString()
//                                        .doOnNext(v -> log.info(" wire log receive data: {}", v))
//                                        .then())
//                        .bindNow();
//        server.onDispose().block();

        // 3 netty event loop group
        LoopResources loop = LoopResources.create("event-loop", 1, 4, true);
        DisposableServer server = TcpServer.create()
                .runOn(loop).bindNow();
        server.onDispose().block();
    }

    private static void lifeCycle() {
        DisposableServer server =
                TcpServer.create()
                        .host("localhost").port(8080)
                        .doOnBind(bind -> {
                            log.info("tcp server bind config {}", bind);
                        })
                        .doOnBound(bound -> {
                            log.info("tcp server bound host {}, port:{}", bound.host(), bound.port());
                        })
                        .doOnChannelInit((observer, channel, remoteAddress) ->{
                            // channel 初始化, 添加日志记录
                            log.info("tcp server channel init {}", remoteAddress);
                            channel.pipeline().addFirst(new LoggingHandler("reactor.netty.examples", LogLevel.INFO));
                        })
                        .doOnConnection(conn -> {
                            log.info("tcp server on connection");
                            conn.addHandlerFirst(new ReadTimeoutHandler(10, TimeUnit.SECONDS));
                        })
                        .handle((inbound, outbound) ->
                                inbound.receive().asString()
                                        .doOnNext(v -> log.info("receive data: {}", v))
                                        .then())
                        .doOnUnbound(unbound -> {
                            log.info("tcp server unbound host {}, port:{}", unbound.host(), unbound.port());
                        })
                        .bindNow();

        server.onDispose()
                .block();
    }

    private static void dataSendRead() {
        // 1 消费读取数据
//        DisposableServer server = TcpServer.create()
//                .host("127.0.0.1").port(8080)
//                .handle((inbound, outbound) ->
//                    inbound.receive().asString()
//                            .doOnNext(v -> log.info("receive data: {}", v))
//                            .then()
//                ).bindNow();
//        server.onDispose().block();

        // 2 链接返回数据
        DisposableServer server = TcpServer.create()
                .port(8080)
                .handle((inbound, outbound) -> {
                    String resp = String.format("Hello, %s!", new Date());
                    log.info("resp is {} current thread is: {}", resp, Thread.currentThread().getName());
                    return outbound.sendString(Mono.just(resp));
                })
                .bindNow();
        server.onDispose().block();
    }

    private static void createServer() {
        // 1 单个服务示例
//        DisposableServer server = TcpServer.create()
//                .host("localhost")
//                .port(8080)
//                .bindNow();
//        server.onDispose().block();

        // 2 多个服务实例
//        TcpServer tcpServer = TcpServer.create();
//        DisposableServer server1 = tcpServer
//                .host("localhost")
//                .port(8080)
//                .bindNow();
//
//        DisposableServer server2 = tcpServer
//                .host("0.0.0.0")
//                .port(8081)
//                .bindNow();
//        Mono.when(server1.onDispose(), server2.onDispose()).block();

        // 3 预热
        TcpServer tcpServer = TcpServer.create()
                .host("localhost").port(8080)
                .handle((inbound, outbound) -> inbound.receive().then());
        tcpServer.warmup().block();
        DisposableServer server = tcpServer.bindNow();
        server.onDispose().block();
    }
}
