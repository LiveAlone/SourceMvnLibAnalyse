package org.yqj.source.sentinel.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: 并发http 请求返回数据结果
 *
 * @author yaoqijun
 * @date 2022/8/4
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class ParallelHttpRequest {

    public static final String baseUrl = "http://localhost:9090/%s";

    public static final String flowUrl = String.format(baseUrl, "flow");

    public static void main(String[] args) throws Exception {
//        parallelHttpRequest();

//        demoWithSource();

//        degradeRequestDemo();

//        authorityRequest();
    }

    public static void authorityRequest() throws Exception {
        int threadCount = 3;
        int maxGapReq = 100;
        String originUrl = String.format(baseUrl, "authority");

        CountDownLatch ct = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int pos = i;
            Thread s = new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                String name = Thread.currentThread().getName();
                String currUrl = originUrl;
                if (pos % 2 == 0) {
                    currUrl = String.format("%s?origin=%s", originUrl, "yao");
                }
                while (true) {
                    Request request = new Request.Builder()
                            .url(currUrl)
                            .build();
                    try (Response response = client.newCall(request).execute()) {
                        log.info("{} gain response is {}", name, response.body().string());
                        Thread.sleep(ThreadLocalRandom.current().nextInt(maxGapReq));
                    } catch (Exception e) {
                        log.error("{} thread request fail", name, e);
                        ct.countDown();
                        break;
                    }
                }
            });
            s.setName(String.format("downgrade_test_thread_%d", i));
            s.start();
        }

        ct.await();
    }

    public static void degradeRequestDemo() throws Exception {
        int threadCount = 10;
        int maxGapReq = 100;
        int failRate = 10;  // 错误比例 0 - 100

        String degradeUrl = String.format(baseUrl, "degrade");

        CountDownLatch ct = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread s = new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                String name = Thread.currentThread().getName();
                int c = 0;

                while (true) {
                    String currUrl;
                    if (c % 100 < failRate) {
                        currUrl = String.format("%s?expType=%d", degradeUrl, 1);
                    } else {
                        currUrl = String.format("%s?expType=%d", degradeUrl, 0);
                    }
                    Request request = new Request.Builder()
                            .url(currUrl)
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        log.info("{} gain response is {}", name, response.body().string());
                        Thread.sleep(ThreadLocalRandom.current().nextInt(maxGapReq));
                    } catch (Exception e) {
                        log.error("{} thread request fail", name, e);
                        ct.countDown();
                        break;
                    }

                    c += 1;
                }
            });
            s.setName(String.format("downgrade_test_thread_%d", i));
            s.start();
        }

        ct.await();
    }

    public static void demoWithSource() throws InterruptedException {
        String currentCurl = String.format("%s?name=%s&origin=%s", flowUrl, "FlowController", "AppDemo");
        for (int i = 0; i < 200; i++) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(currentCurl)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                log.info("gain response is {}", response.body().string());
            } catch (Exception e) {
                log.info("gain exception context is ", e);
            }
            Thread.sleep(100);
        }
    }

    public static void parallelHttpRequest() throws Exception {
        int parallelClientCount = 20;
        int maxClientRequestGap = 500;

        CountDownLatch ct = new CountDownLatch(maxClientRequestGap);

        for (int i = 0; i < parallelClientCount; i++) {
            Thread s = new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(flowUrl)
                        .build();
                String name = Thread.currentThread().getName();
                while (true) {
                    try (Response response = client.newCall(request).execute()) {
                        log.info("{} gain response is {}", name, response.body().string());
                        Thread.sleep(ThreadLocalRandom.current().nextInt(maxClientRequestGap));
                    } catch (Exception e) {
                        log.error("{} thread request fail", name, e);
                        ct.countDown();
                        break;
                    }
                }
            });
            s.setName(String.format("parallelMockRequest-%d", i));
            s.start();
        }

        ct.await();
    }

    public static void demo() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(flowUrl)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                log.info("gain response is {}", response.body().string());
            } catch (Exception e) {
                log.info("gain exception context is ", e);
            }
            Thread.sleep(100);
        }
    }
}
