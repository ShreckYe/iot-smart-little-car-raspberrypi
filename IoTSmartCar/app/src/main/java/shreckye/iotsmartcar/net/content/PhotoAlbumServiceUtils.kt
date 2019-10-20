package shreckye.iotsmartcar.net.content

import java.net.URL

fun getPhotoUrl(host: String, port: Int = DEFAULT_PORT, filename: String): String =
    URL("http", host, port, filename).toExternalForm()
