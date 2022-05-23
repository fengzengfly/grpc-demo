package com.xiaofengstu.news;

import com.xiaofengstu.news.proto.NewsProto;
import com.xiaofengstu.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * @Author FengZeng
 * @Date 2022-05-23 09:46
 * @Description TODO
 */
public class NewsClient {
  private static final String HOST = "localhost";
  private static final int PORT = 9999;

  public static void main(String[] args) {

    ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
    NewsClient client = new NewsClient();
    try {
      //client.getNews(channel);
      client.hello(channel);
    }finally {
      channel.shutdown();
    }
  }

  public void getNews(ManagedChannel channel) {
    NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);
    NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder().setData("2022-5-23").build();
    NewsProto.NewsResponse list = blockingStub.list(request);
    List<NewsProto.News> newsList = list.getNewsList();
    for (NewsProto.News news : newsList) {
      System.out.println(news.getTitle() + "，内容：" + news.getContent());
    }
  }

  public void hello(ManagedChannel channel) {
    NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);
    NewsProto.StringRequest request = NewsProto.StringRequest.newBuilder().setHi("hello").build();
    NewsProto.StringResponse response = blockingStub.hello(request);
    System.out.println(response.getResult());
  }
}
