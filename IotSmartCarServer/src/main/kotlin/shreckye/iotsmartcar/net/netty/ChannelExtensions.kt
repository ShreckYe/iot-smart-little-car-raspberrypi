package shreckye.iotsmartcar.net.netty

import io.netty.channel.Channel
import io.netty.util.concurrent.Future
import io.netty.util.concurrent.GenericFutureListener

object OutboundExceptionHandlerListener : GenericFutureListener<Future<Void>> {
    override fun operationComplete(future: Future<Void>) {
        try {
            future.get()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}

fun Channel.writeE(msg: Any?) {
    write(msg).addListener(OutboundExceptionHandlerListener)
}

fun Channel.writeAndflushE(msg: Any?) {
    writeAndFlush(msg).addListener(OutboundExceptionHandlerListener)
}