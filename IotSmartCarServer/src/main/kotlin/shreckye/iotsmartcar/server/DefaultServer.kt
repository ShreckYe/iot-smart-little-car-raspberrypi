package shreckye.iotsmartcar.server

import shreckye.iotsmartcar.IotSmartCar
import shreckye.iotsmartcar.server.net.content.PhotoAlbumServer
import shreckye.iotsmartcar.server.net.control.ControlServer
import shreckye.iotsmartcar.server.net.reliable.ReliableServer

class DefaultServer : AutoCloseable {
    val iotSmartCar = IotSmartCar() /*{ streamServer.broadcastImage(it) }*/

    val photoAlbumServer = PhotoAlbumServer()
    val controlServer = ControlServer(iotSmartCar)
    val reliableServer = ReliableServer(iotSmartCar)
    // The stream server is now implemented in Python
    //val streamServer = StreamServer()


    override fun close() {
        photoAlbumServer.close()
        controlServer.close()
        reliableServer.close()

        //streamServer.close()

        iotSmartCar.close()
    }
}