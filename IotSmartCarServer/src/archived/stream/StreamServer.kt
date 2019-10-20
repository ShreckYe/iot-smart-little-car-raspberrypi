package shreckye.iotsmartcar.server.net.stream

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufOutputStream
import io.netty.buffer.Unpooled
import io.netty.channel.*
import io.netty.channel.group.ChannelGroup
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.util.concurrent.GlobalEventExecutor
import shreckye.iotsmartcar.net.stream.STREAM_SERVICE_DEFAULT_PORT
import java.awt.image.BufferedImage
import java.lang.Exception
import javax.imageio.ImageIO
import kotlin.concurrent.thread

class StreamServer(val port: Int = STREAM_SERVICE_DEFAULT_PORT) {
    val bossGroup: EventLoopGroup = NioEventLoopGroup(1)
    val workerGroup: EventLoopGroup = NioEventLoopGroup()
    val serverChannel: Channel = ServerBootstrap()
            .group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel) {
                    ch.pipeline().addLast(object : SimpleChannelInboundHandler<ByteBuf>() {
                        override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
                            cause.printStackTrace()
                        }

                        override fun channelActive(ctx: ChannelHandlerContext) {
                            println("Stream connected: ${ctx.channel()}")
                            channelGroup.add(ctx.channel())
                        }

                        override fun channelInactive(ctx: ChannelHandlerContext) {
                            channelGroup.remove(ctx.channel())
                        }

                        override fun channelRead0(ctx: ChannelHandlerContext, msg: ByteBuf) {
                            // Do nothing
                        }
                    })
                }
            })
            .bind(port).sync()
            .channel()
    val channelGroup: ChannelGroup = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)

    companion object {
        private const val BUFFER_SIZE = 0x80000
    }

    fun broadcastImage(image: BufferedImage) {
        val jpegBuf = Unpooled.buffer(BUFFER_SIZE)
        val out = ByteBufOutputStream(jpegBuf)
        ImageIO.write(image, "jpg", out)
        for (channel in channelGroup) {
            //println("Streaming a frame to channel: ${jpegBuf.writerIndex()}, $channel")
            val lengthBuf = Unpooled.buffer(Integer.BYTES)
            lengthBuf.writeInt(jpegBuf.writerIndex())
            val f1 = channel.write(lengthBuf)
            val f2 = channel.writeAndFlush(jpegBuf)

            /*thread {
                f1.sync()
                f2.sync()
            }*/
        }
    }

    fun close() {
        serverChannel.close()
    }
}