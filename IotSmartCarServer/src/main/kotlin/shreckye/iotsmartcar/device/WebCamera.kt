package shreckye.iotsmartcar.device

import com.github.sarxos.webcam.Webcam
import com.github.sarxos.webcam.WebcamEvent
import com.github.sarxos.webcam.WebcamListener
import java.awt.image.BufferedImage

class WebCamera(inputListener: ((BufferedImage) -> Unit)? = null) : ComprehensiveInputDevice<BufferedImage>, CloseableDevice {
    override val type: String = "camera"
    override var inputListener: ((BufferedImage) -> Unit)? = inputListener
        set(value) {
            field = value
            webcam.webcamListeners.forEach { webcam.removeWebcamListener(it) }
            if (value != null)
                webcam.addWebcamListener(object : WebcamListener {
                    override fun webcamOpen(we: WebcamEvent) {}

                    override fun webcamImageObtained(we: WebcamEvent) {
                        value(we.image)
                    }

                    override fun webcamClosed(we: WebcamEvent) {}

                    override fun webcamDisposed(we: WebcamEvent) {}
                })
        }

    val webcam: Webcam = Webcam.getDefault()

    init {
        webcam.open()
    }

    override fun close() {
        webcam.close()
    }

    override fun retrieveInput(): BufferedImage = webcam.image

    override fun awaitInput(): BufferedImage = webcam.image

    fun capture(): BufferedImage {
        return retrieveInput()
    }
}