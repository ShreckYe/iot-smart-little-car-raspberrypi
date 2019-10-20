package shreckye.iotsmartcar.server.net.content

import shreckye.asynchttpserver.codec.FullRequest
import shreckye.asynchttpserver.service.NoResourcesService
import shreckye.asynchttpserver.service.SimpleFileService
import shreckye.iotsmartcar.server.content.photosDirectory
import java.io.File

class PhotoAlbumService : SimpleFileService(), NoResourcesService {
    override fun file(fullRequest: FullRequest): File =
            File(photosDirectory, fullRequest.path())
}
