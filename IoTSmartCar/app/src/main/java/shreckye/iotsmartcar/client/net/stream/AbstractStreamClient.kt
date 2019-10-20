package shreckye.iotsmartcar.client.net.stream

import android.util.Log
import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.MessageToMessageCodec
import shreckye.iotsmartcar.data.RecognitionPrediction
import shreckye.iotsmartcar.data.protocol.stream.StreamMessage
import shreckye.iotsmartcar.net.stream.STREAM_SERVICE_DEFAULT_PORT
import java.io.InputStream
import java.nio.charset.Charset

abstract class AbstractStreamClient<Image>(
    var imageReceivedListener: (Image) -> Unit,
    var recognitionPredictionsReceivedListener: (List<RecognitionPrediction>) -> Unit,
    val host: String,
    val port: Int = STREAM_SERVICE_DEFAULT_PORT
) {
    val group = NioEventLoopGroup(1)
    val channel = Bootstrap()
        .group(group)
        .channel(NioSocketChannel::class.java)
        .handler(object : ChannelInitializer<SocketChannel>() {
            override fun initChannel(ch: SocketChannel) {
                ch.pipeline()
                    .addLast(object : MessageToMessageCodec<ByteBuf, StreamMessage>() {
                        override fun encode(ctx: ChannelHandlerContext, msg: StreamMessage, out: MutableList<Any>) {
                            // Encoder temporarily not needed
                            val sizeAndActionBuffer = Unpooled.buffer(5)
                            sizeAndActionBuffer.writeByte(msg.action.toInt())
                            sizeAndActionBuffer.writeInt(msg.size)
                            out.add(sizeAndActionBuffer)
                            out.add(msg.content)
                        }

                        var inStreamMessage: StreamMessage? = null
                        override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
                            Log.d("netty", msg.toString())
                            while (true) {
                                val content: ByteBuf
                                if (inStreamMessage == null) {
                                    val action = msg.readByte()
                                    val size = msg.readInt()
                                    Log.d("action_size", "$action $size")
                                    content = Unpooled.buffer(size, size)
                                    inStreamMessage = StreamMessage(action, content)
                                } else
                                    content = inStreamMessage!!.content

                                val contentLeft = content.capacity() - content.readableBytes()
                                val msgLength = msg.readableBytes()
                                if (msgLength < contentLeft) {
                                    content.writeBytes(msg)
                                    break
                                } else if (msgLength == contentLeft) {
                                    content.writeBytes(msg)
                                    streamMessageComplete(out)
                                    break
                                } else {
                                    content.writeBytes(msg, contentLeft)
                                    streamMessageComplete(out)
                                }
                            }
                        }

                        fun streamMessageComplete(out: MutableList<Any>) {
                            out.add(inStreamMessage!!)
                            inStreamMessage = null
                        }
                    })
                    /*.addLast(object : SimpleChannelInboundHandler<ByteBuf>() {
                        var jpegBuffer: ByteBuf? = null
                        override fun channelRead0(ctx: ChannelHandlerContext, msg: ByteBuf) {
                            if (jpegBuffer === null) {
                                val size = msg.readInt()
                                jpegBuffer = Unpooled.buffer(size, size)
                            }

                            val jpegLeft = jpegBuffer!!.capacity() - jpegBuffer!!.readableBytes()
                            val msgLength = msg.readableBytes()
                            if (msgLength < jpegLeft)
                                jpegBuffer!!.writeBytes(msg)
                            else if (msgLength == jpegLeft) {
                                jpegBuffer!!.writeBytes(msg)
                                fireJpegReadComplete(ctx)
                            } else {
                                jpegBuffer!!.writeBytes(msg, jpegLeft)
                                fireJpegReadComplete(ctx)
                            }
                        }

                        fun fireJpegReadComplete(ctx: ChannelHandlerContext) {
                            ctx.fireChannelRead(jpegBuffer)
                            jpegBuffer = null
                        }
                    })*/
                    .addLast(object : SimpleChannelInboundHandler<StreamMessage>() {
                        override fun channelRead0(ctx: ChannelHandlerContext, msg: StreamMessage) {
                            Log.d("netty", msg.toString())
                            when (msg.action) {
                                StreamMessage.ACTION_IMAGE ->
                                    imageReceivedListener(
                                        readJpegStream(ByteBufInputStream(msg.content))
                                    )
                                StreamMessage.ACTION_RECOGNITION_PREDICTIONS ->
                                    recognitionPredictionsReceivedListener(
                                        readRecognitionPredictions(msg.content)
                                    )
                                else ->
                                    throw IllegalArgumentException("invalid action: ${msg.action}")
                            }
                        }
                    })
            }
        })
        .connect(host, port).sync()
        .channel()

    abstract fun readJpegStream(`in`: InputStream): Image
    fun readRecognitionPredictions(byteBuf: ByteBuf): List<RecognitionPrediction> {
        val size = byteBuf.readByte()
        val predictions = ArrayList<RecognitionPrediction>(size.toInt())
        for (i in 0 until size) {
            val tagSize = byteBuf.readByte()
            predictions.add(
                RecognitionPrediction(
                    byteBuf.readCharSequence(tagSize.toInt(), Charset.defaultCharset()).toString(),
                    byteBuf.readFloat()
                )
            )
        }
        return predictions
    }

    fun close() {
        channel.close()
        group.shutdownGracefully()
    }
}