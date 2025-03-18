package org.yqj.source.reactor.netty.doc;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author 10126730
 * Date: 2025/3/18 13:42
 * Description:
 */
@Slf4j
public class TcpClientDemo {
    public static void main(String[] args) {

        tcpConfig();

//        lifeCycle();

//        dataReadWrite();

//        createClient();
    }

    private static void poolShare() {
        ConnectionProvider provider =
                ConnectionProvider.builder("fixed")
                        .maxConnections(50)
                        .pendingAcquireTimeout(Duration.ofSeconds(60))
                        .build();

        Connection connection = TcpClient.create(provider)
                .host("example.com").port(80)
                .connectNow();

        connection.onDispose()
                .block();
    }

    private static void tcpConfig() {
//        Connection connection = TcpClient.create().host("example.com").port(80)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000).connectNow();
//        connection.onDispose().block();

        // wire log
        Connection connection = TcpClient.create()
                .wiretap("logger-name", LogLevel.INFO, AdvancedByteBufFormat.HEX_DUMP)
                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")))
                .host("localhost").port(8080).connectNow();
        connection.onDispose().block();
    }

    private static void lifeCycle() {
        // 完成生命周期管理注册
        Connection connection = TcpClient.create()
                .host("localhost").port(8080)
                // 1. first connect
                .doOnConnect(config -> {
                    log.info("tcp client connect config: {}", config);
                })
                // 2. resolve
                .doOnResolve(conn -> {
                    log.info("tcp client resolve");
                })
                .doAfterResolve((conn, address) -> {
                    log.info("tcp client after resolve :{}", address);
                })
                .doOnResolveError((conn, fail) -> {
                    log.info("tcp client error:", fail);
                })

                .doOnConnected(conn -> {
                    log.info("tcp client connected");
                    conn.addHandlerFirst(new ReadTimeoutHandler(10, TimeUnit.SECONDS));
                })
                .doOnChannelInit((observer, channel, remoteAddress) -> {
                    log.info("tcp client channel init {}", remoteAddress);
                    channel.pipeline().addFirst(new LoggingHandler("reactor.netty.examples", LogLevel.INFO));
                })
                .doOnDisconnected(conn -> {
                    log.info("tcp client disconnected");
                })
                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")))
                .connectNow();
        connection.onDispose().block();
    }

    private static void dataReadWrite() {
        // 1 handler write 写入数据
//        Connection connection = TcpClient.create()
//                .host("127.0.0.1").port(8080)
//                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")))
//                .connectNow();
//        connection.onDispose().block();

        // 2 connect write 写入数据
//        Connection connection = TcpClient.create()
//                .host("127.0.0.1").port(8080)
//                .connectNow();
//        connection.outbound()
//                .sendString(Mono.just("hello 1")) //<1>
//                .then()
//                .subscribe();
//        connection.outbound()
//                .sendString(Mono.just("hello 2")) //<2>
//                .then()
//                .subscribe(null, null, connection::dispose);
//        connection.onDispose().block();

        // 3 client read 读取数据
//        Connection connection = TcpClient.create()
//                .host("127.0.0.1").port(8080)
//                .handle((inbound, outbound) -> {
//                    return inbound.receive().asString().doOnNext(v -> log.info("received: {}", v)).then();
////                    return outbound.sendString(Mono.just("hello"));
//                })
//                .connectNow();
//        connection.onDispose().block();

        // 4 阻塞式读取数据
//        Connection connection = TcpClient.create()
//                .host("localhost").port(8080)
//                .connectNow();
//        connection.inbound().receive().asString().subscribe(v -> {
//            log.info("received: {}", v);
//        });
//        connection.onDispose().block();
    }

    private static void createClient() {
//        Connection connection = TcpClient.create()
//                .host("localhost")
//                .port(8080)
//                .connectNow();
//        connection.onDispose().block();

        // 2 预热
        TcpClient tcpClient = TcpClient.create()
                        .host("localhost")
                        .port(8080)
                        .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")));
        tcpClient.warmup().block();
        Connection connection = tcpClient.connectNow();
        connection.onDispose().block();
    }
}
