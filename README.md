# Micronaut Issue with Http Client sending empty GET prams

The micronaut http client sends all properties of a pojo even if they are empty -> **http://localhost:8080/?type=&name=John**.
This causes an error (see [Stacktrace](#stacktrace)) when one of the empty properties is an enum. 

## Reproduce Error

`./gradlew test --tests "net.safri.FailingClientTest"`

## Workaround

Setting `jackson.deserialization.read-unknown-enum-values-as-null` to `true` prevents the error

`./gradlew test --tests "net.safri.WorkaroundClientTest"`

## Stacktrace

```java
09:50:35.543 [Test worker] ERROR i.m.r.intercept.RecoveryInterceptor - Type [net.safri.TestClient$Intercepted] executed with error: Failed to convert argument [type] for value [] due to: Cannot deserialize value of type `net.safri.Type` from String "": value not one of declared Enum instance names: [A, B]
 at [Source: UNKNOWN; line: -1, column: -1] (through reference chain: net.safri.TestCommand["type"])
io.micronaut.http.client.exceptions.HttpClientResponseException: Failed to convert argument [type] for value [] due to: Cannot deserialize value of type `net.safri.Type` from String "": value not one of declared Enum instance names: [A, B]
 at [Source: UNKNOWN; line: -1, column: -1] (through reference chain: net.safri.TestCommand["type"])
	at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1789)
	at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1729)
	at io.netty.channel.SimpleChannelInboundHandler.channelRead(SimpleChannelInboundHandler.java:105)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.micronaut.http.netty.stream.HttpStreamsHandler.channelRead(HttpStreamsHandler.java:185)
	at io.micronaut.http.netty.stream.HttpStreamsClientHandler.channelRead(HttpStreamsClientHandler.java:180)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:102)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:102)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:438)
	at io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:321)
	at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:295)
	at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:253)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.netty.handler.timeout.IdleStateHandler.channelRead(IdleStateHandler.java:287)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1408)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:930)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:163)
	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:697)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:632)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:549)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:511)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:918)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)
```