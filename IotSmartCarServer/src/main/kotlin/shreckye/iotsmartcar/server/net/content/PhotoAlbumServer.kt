package shreckye.iotsmartcar.server.net.content

import io.netty.channel.ChannelFuture
import shreckye.asynchttpserver.AsyncHttpServer
import shreckye.iotsmartcar.net.content.PHOTO_ALBUM_SERVICE_DEFAULT_PORT

class PhotoAlbumServer {
    val asyncHttpServer: AsyncHttpServer = AsyncHttpServer.Builder()
            .registerDefault { PhotoAlbumService() }
            .port(PHOTO_ALBUM_SERVICE_DEFAULT_PORT)
            .buildAndStart()

    fun close() = asyncHttpServer.close()
    fun closeAsync(): ChannelFuture = asyncHttpServer.closeAsync()
}