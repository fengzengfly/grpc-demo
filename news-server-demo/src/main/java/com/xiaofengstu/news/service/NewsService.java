package com.xiaofengstu.news.service;

import com.xiaofengstu.news.proto.NewsProto;
import com.xiaofengstu.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Date;

/**
 * @Author FengZeng
 * @Date 2022-05-23 01:25
 * @Description TODO
 */
public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {
  /**
   * 双工协议
   * @param request
   * @param responseObserver 返回值，responseObserver 观察者对象，观察 NewsProto.NewsRequest 的变化
   */
  @Override
  public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsResponse> responseObserver) {
    String date = request.getData();
    NewsProto.NewsResponse newsList = null;
    try {
      NewsProto.NewsResponse.Builder newsListBuilder = NewsProto.NewsResponse.newBuilder();
      for (int i = 0; i < 100; i++) {
        NewsProto.News news = NewsProto.News.newBuilder().setId(i)
            .setContent(date + "当日新闻内容" + i)
            .setTitle("新闻标题" + i)
            .setCreateTime(System.currentTimeMillis())
            .build();
        newsListBuilder.addNews(news);
      }
      newsList = newsListBuilder.build();
    } catch (Exception e) {
      responseObserver.onError(e);
    }finally {
      responseObserver.onNext(newsList);
    }
    responseObserver.onCompleted();
  }

  @Override
  public void hello(NewsProto.StringRequest request, StreamObserver<NewsProto.StringResponse> responseObserver) {
    String hi = request.getHi();
    NewsProto.StringResponse response = NewsProto.StringResponse.newBuilder().setResult(hi + " world").build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
