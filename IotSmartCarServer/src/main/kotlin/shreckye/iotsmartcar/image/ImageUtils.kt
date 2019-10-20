package shreckye.iotsmartcar.image

import shreckye.iotsmartcar.server.content.EXTENSION_JPG
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.RenderedImage
import java.io.OutputStream
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam

fun writeImageBestJpg(image: RenderedImage, outputstream: OutputStream) {
    val imageWriter = ImageIO.getImageWritersBySuffix(EXTENSION_JPG).next()
    imageWriter.output = ImageIO.createImageOutputStream(outputstream)

    val imageWriteParam = imageWriter.defaultWriteParam
    imageWriteParam.compressionMode = ImageWriteParam.MODE_EXPLICIT
    imageWriteParam.compressionQuality = 1f

    imageWriter.write(image)
}

fun resize(image: Image, newWidth: Int, newHeight: Int): BufferedImage {
    val targetBufferedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)

    val graphics = targetBufferedImage.createGraphics()
    graphics.drawImage(image, 0, 0, newWidth, newHeight, null)
    graphics.dispose()

    return targetBufferedImage
}

fun Image.flipAndResize(newWidth: Int, newHeight: Int): BufferedImage {
    val targetBufferedImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)

    val graphics = targetBufferedImage.createGraphics()
    graphics.drawImage(this, 0, 0, -newWidth, -newHeight, null)
    graphics.dispose()

    return targetBufferedImage
}