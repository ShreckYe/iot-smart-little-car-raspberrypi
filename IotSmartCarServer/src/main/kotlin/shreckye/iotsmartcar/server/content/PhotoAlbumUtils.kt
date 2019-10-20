package shreckye.iotsmartcar.server.content

import shreckye.iotsmartcar.image.writeImageBestJpg
import java.awt.image.RenderedImage
import java.io.File
import java.io.FileOutputStream
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


const val EXTENSION_JPG = "jpg"
val userHomePath: String = System.getProperty("user.home")
val photosDirectory: File = File(userHomePath, "photos")
fun getPhotoFilenames(): Array<String> {
    return photosDirectory.list()
}

fun getPhotoFile(filename: String): File {
    return File(photosDirectory, filename)
}

fun saveNewPhotoBestJpg(image: RenderedImage, filename: String = getTimeFilename()) {
    photosDirectory.mkdirs()
    val file = getPhotoFile(filename)
    file.createNewFile()
    FileOutputStream(file).use { writeImageBestJpg(image, it) }
}

fun getTimeFilename(): String {
    return "${ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS_x"))}.jpg"
}