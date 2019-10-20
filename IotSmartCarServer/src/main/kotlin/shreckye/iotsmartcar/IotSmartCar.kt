package shreckye.iotsmartcar

import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.device.PiCamera
import shreckye.iotsmartcar.device.Wheels
import shreckye.iotsmartcar.server.content.saveNewPhotoBestJpg
import java.awt.image.BufferedImage
import kotlin.concurrent.thread

// The camera is now implemented in Python
class IotSmartCar(/*private val imageListener: (BufferedImage) -> Unit = {}*/) : AutoCloseable {
    val wheels: Wheels = Wheels()
    //val holder: Holder = Holder()
    //val camera: PiCamera = PiCamera()
    //val camera: WebCamera = WebCamera()

    /*val streamThread = thread {
        while (!Thread.interrupted()) {
            val lastFrameTime = System.currentTimeMillis()
            val image = capture()
            latestImage = image
            //imageListener(image)

            val elapsedTime = System.currentTimeMillis() - lastFrameTime
            if (elapsedTime < 40)
                Thread.sleep(40 - elapsedTime)
        }
    }
    var latestImage: BufferedImage? = null
    fun saveLatestImage() {
        saveNewPhotoBestJpg(latestImage!!)
    }*/

    fun moveWheels(wheelsControl: IotSmartCarProto.WheelsControl) {
        wheels.move(wheelsControl)
    }

    //fun moveHolder() = holder.move()

    //fun capture() = camera.capture()
    /*fun captureAndSave() {
        val image = camera.capture()
        saveNewPhotoBestJpg(image)
    }*/

    override fun close() {
        //streamThread.interrupt()

        wheels.close()
        //camera.close()
    }
}
