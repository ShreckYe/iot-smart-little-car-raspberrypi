package shreckye.iotsmartcar.data.protocol.stream

import io.netty.buffer.ByteBuf

class StreamMessage(val action: Byte, val content: ByteBuf) {
    companion object {
        const val ACTION_IMAGE: Byte = 1
        const val ACTION_RECOGNITION_PREDICTIONS: Byte = 2
    }

    val size: Int get() = content.writerIndex()
}