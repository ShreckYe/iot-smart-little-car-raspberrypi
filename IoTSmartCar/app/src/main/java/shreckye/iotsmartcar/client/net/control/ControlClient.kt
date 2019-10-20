package shreckye.iotsmartcar.client.net.control

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.DatagramPacket
import io.netty.channel.socket.nio.NioDatagramChannel
import io.netty.util.internal.SocketUtils
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.net.control.CONTROL_SERVICE_DEFAULT_PORT
import java.io.Closeable

class ControlClient(val host: String, val port: Int = CONTROL_SERVICE_DEFAULT_PORT) : Closeable {
    val eventLoopGroup: EventLoopGroup = NioEventLoopGroup(1)
    val clientChannel: Channel = Bootstrap()
        .group(eventLoopGroup)
        .channel(NioDatagramChannel::class.java)
        .handler(object : SimpleChannelInboundHandler<DatagramPacket>() {
            override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
                cause.printStackTrace()
            }

            override fun channelRead0(ctx: ChannelHandlerContext, msg: DatagramPacket) {
            }
        })
        .connect(host, port).sync().channel()

    fun sendControl(motionControl: IotSmartCarProto.MotionControl): ChannelFuture {
        return clientChannel.writeAndFlush(
            DatagramPacket(
                Unpooled.wrappedBuffer(motionControl.toByteArray()),
                SocketUtils.socketAddress(host, port)
            )
        )
    }

    fun sendControlSync(motionControl: IotSmartCarProto.MotionControl) {
        sendControl(motionControl).sync()
        sendControl(motionControl).addListener {  }
    }

    override fun close() {
        clientChannel.close()
        eventLoopGroup.shutdownGracefully()
    }
}