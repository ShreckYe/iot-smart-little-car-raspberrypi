package shreckye.iotsmartcar.net.content

import java.net.URL

fun getPhotoUrl(host: String, port: Int = PHOTO_ALBUM_SERVICE_DEFAULT_PORT, filename: String): String {
    return URL("http", host, port, filename).toExternalForm()
}
