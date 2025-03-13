package org.yqj.source.reactor.netty;

import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

/**
 * @author 10126730
 * Date: 2025/3/13 09:43
 * Description:
 */
public class LocalTcpClient {
    public static void main(String[] args) {
        clientCreate();
    }

    private static void writeData() {
        // 1 data write
//        Connection connection = TcpClient.create().host("example.com").port(80).connectNow();
//        connection.outbound()
//                .sendString(Mono.just("hello 1"))
//                .then()
//                .subscribe();
//        connection.outbound()
//                .sendString(Mono.just("hello 2"))
//                .then()
//                .subscribe(null, null, connection::dispose);
//        connection.onDispose().block();

        // 2 consume data
        Connection connection = TcpClient.create()
                .host("example.com").port(80)
                .handle((inbound, outbound) -> inbound.receive().then())
                .connectNow();
        // handle 类似操作链，可以在其中进行数据处理
        connection.inbound().receive()
                .then().subscribe();
        connection.onDispose().block();
    }

    private static void clientCreate() {
//        Connection connection = TcpClient.create().connectNow();
//        connection.onDispose().block();

//        Connection connection = TcpClient.create().host("127.0.0.1").port(8080).connectNow();
//        connection.onDispose().block();

        // 1 warm up write data
        TcpClient tcpClient = TcpClient.create().host("example.com").port(80)
                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello")));
        tcpClient.warmup().block();
        Connection connection = tcpClient.connectNow();
        connection.onDispose().block();
    }
}
