package org.yqj.source.reactor.netty;

import io.netty.channel.ChannelOption;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpServer;
import reactor.netty.tcp.TcpSslContextSpec;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author 10126730
 * Date: 2025/3/12 20:21
 * Description:
 */
public class LocalTcpServer {

    public static void main(String[] args) {
//        appInit();

//        handlerMessage();

        createServer();
    }

    public static void createServer() {
        DisposableServer server = TcpServer.create()
                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")))
                .host("127.0.0.1").port(8080).bindNow();
        server.onDispose().block();
    }

    public static void metricTrace() {
        // 1 metric, 支持自定义集成 Micrometer
        DisposableServer server = TcpServer.create().metrics(true).bindNow();
        server.onDispose().block();

        // 2 支持 zipkin， jaeger 链路追踪
    }

    public static void sslTls() {
//        File cert = new File("certificate.crt");
//        File key = new File("private.key");
//        TcpSslContextSpec tcpSslContextSpec = TcpSslContextSpec.forServer(cert, key);
//        DisposableServer server = TcpServer.create()
//                .secure(spec -> spec.sslContext(tcpSslContextSpec)).bindNow();
//        server.onDispose().block();

//        File defaultCert = new File("default_certificate.crt");
//        File defaultKey = new File("default_private.key");
//
//        File testDomainCert = new File("default_certificate.crt");
//        File testDomainKey = new File("default_private.key");
//
//        SslContext defaultSslContext = SslContextBuilder.forServer(defaultCert, defaultKey).build();
//        SslContext testDomainSslContext = SslContextBuilder.forServer(testDomainCert, testDomainKey).build();
//
//        DisposableServer server =
//                TcpServer.create()
//                        .secure(spec -> spec.sslContext(defaultSslContext)
//                                .addSniMapping("*.test.com",
//                                        testDomainSpec -> testDomainSpec.sslContext(testDomainSslContext)))
//                        .bindNow();
//
//        server.onDispose()
//                .block();
    }

    public static void handlerMessage() {
//        DisposableServer server = TcpServer.create()
//                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")))
//                .bindNow();
//        server.onDispose().block();

        // inbound 为接收数据的处理
//        DisposableServer server = TcpServer.create()
//                .handle((inbound, outbound) -> inbound.receive().then())
//                .bindNow();
//        server.onDispose().block();

        // netty handler
//        DisposableServer server = TcpServer.create()
//                .doOnConnection(conn ->
//                        conn.addHandlerFirst(new ReadTimeoutHandler(10, TimeUnit.SECONDS)))
//                .doOnChannelInit((observer, channel, remoteAddress) ->
//                        channel.pipeline().addFirst(new LoggingHandler("LocalNettyInitReactor")))
//                .bindNow();
//        server.onDispose().block();

        // 1 channel options
        // 支持 Common Epoll KQueue Socket Opt 设置
//        DisposableServer server = TcpServer.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                .bindNow();
//        server.onDispose().block();

        // 2 reactor log tcp message, 打印输入输出数据
//        DisposableServer server = TcpServer.create()
//                .wiretap(true)
//                .bindNow();
//        server.onDispose().block();

        // 3 配置Netty EventLoop线程数量
//        LoopResources loop = LoopResources.create("event-loop", 1, 4, true);
//        DisposableServer server = TcpServer.create().runOn(loop).bindNow();
//        server.onDispose().block();

    }

    public static void appInit() {
        // 单服务启动
//        DisposableServer server = TcpServer.create()
//                .host("localhost").port(8080)
//                .bindNow();
//        server.onDispose().block();

        // 2 多服务启动, 阻塞等待执行完成
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
//
//        Mono.when(server1.onDispose(), server2.onDispose())
//                .block();

        // 3 warn up
//        TcpServer tcpServer = TcpServer.create()
//                .handle((inbound, outbound) -> inbound.receive().then());
//        tcpServer.warmup().block();
//        DisposableServer server = tcpServer.bindNow();
//        server.onDispose().block();

        // 3 socket domain 绑定
//        DisposableServer server =
//                TcpServer.create()
//                        // The configuration below is available only when Epoll/KQueue transport is used
//                        .bindAddress(() -> new DomainSocketAddress("/tmp/test.sock"))
//                        // The configuration below is available only when NIO transport is used with Java 17+
//                        //.bindAddress(() -> UnixDomainSocketAddress.of("/tmp/test.sock"))
//                        .bindNow();
//        server.onDispose()
//                .block();
    }
}
