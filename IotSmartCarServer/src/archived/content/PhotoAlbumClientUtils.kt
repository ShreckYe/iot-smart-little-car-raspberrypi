package shreckye.iotsmartcar.client.net.content

import java.net.URL

fun getPhotoUrl(host: String, filename: String) = URL("http", host, filename).toExternalForm()