package com.xiaofengstu.news;

import com.xiaofengstu.news.service.NewsService;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @Author FengZeng
 * @Date 2022-05-23 01:36
 * @Description TODO
 */
public class GrpcServer {
  private static final int PORT = 9999;
  public static void main(String[] args) throws IOException, InterruptedException {
    io.grpc.Server server = ServerBuilder.forPort(PORT).addService(new NewsService()).build().start();
    System.out.println(String.format("GRPC服务端启动成功，端口号：%d.", PORT));
    server.awaitTermination();
  }
}
