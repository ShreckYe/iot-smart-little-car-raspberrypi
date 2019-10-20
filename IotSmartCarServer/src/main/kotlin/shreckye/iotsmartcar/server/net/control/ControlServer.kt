package shreckye.iotsmartcar.server.net.control

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBufInputStream
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.DatagramPacket
import io.netty.channel.socket.nio.NioDatagramChannel
import shreckye.iotsmartcar.IotSmartCar
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.net.control.CONTROL_SERVICE_DEFAULT_PORT

class ControlServer(val iotSmartCar: IotSmartCar, val port: Int = CONTROL_SERVICE_DEFAULT_PORT) : AutoCloseable {
    val group = NioEventLoopGroup(1)
    val serverChannel = Bootstrap()
            .group(group)
            .channel(NioDatagramChannel::class.java)
            //.option(ChannelOption.SO_BROADCAST, true)
            .handler(NettyHandler())
            .bind(port).sync()
            .channel()

    override fun close() {
        serverChannel.close().sync()
        group.shutdownGracefully().sync()
    }

    inner class NettyHandler : SimpleChannelInboundHandler<DatagramPacket>() {
        override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
            cause.printStackTrace()
        }

        override fun channelRead0(ctx: ChannelHandlerContext, msg: DatagramPacket) {
            println("Message received from control client: $msg")

            val motionControl = IotSmartCarProto.MotionControl.parseFrom(ByteBufInputStream(msg.content()))
            iotSmartCar.moveWheels(motionControl.wheelsControl)
        }
    }
}
