package shreckye.iotsmartcar.device

import com.hopding.jrpicam.RPiCamera
import java.awt.image.BufferedImage


class PiCamera : PassiveInputDevice<BufferedImage> {
    override val type: String = "camera"

    val rpiCamera = RPiCamera()
            .turnOffPreview()
            .setWidth(1280)
            .setHeight(960)
            .setVerticalFlipOn()
            .setHorizontalFlipOn()

    override fun retrieveInput(): BufferedImage =
            rpiCamera.takeBufferedStill()

    fun capture(): BufferedImage = retrieveInput()
}