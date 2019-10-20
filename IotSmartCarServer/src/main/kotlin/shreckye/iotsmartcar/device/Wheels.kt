package shreckye.iotsmartcar.device

import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.RaspiPin
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto.WheelsControl
import shreckye.iotsmartcar.data.GO_BACK
import shreckye.iotsmartcar.data.GO_FORWARD
import shreckye.iotsmartcar.data.TURN_LEFT
import shreckye.iotsmartcar.data.TURN_RIGHT

class Wheels : OutputDevice<WheelsControl>, CloseableDevice {
    override val type: String = "wheels"
    val gpioController = GpioFactory.getInstance()
    override fun close() {
        gpioController.shutdown()
    }

    companion object {
        private val leftWheelsForwardPin = RaspiPin.GPIO_26
        private val leftWheelsBackwardPin = RaspiPin.GPIO_24
        private val rightWheelsForwardPin = RaspiPin.GPIO_01
        private val rightWheelsBackwardPin = RaspiPin.GPIO_23

        //val PWM_MAX = 1024
    }

    val leftWheelsForward = gpioController.provisionDigitalOutputPin(leftWheelsForwardPin)
    val leftWheelsBackward = gpioController.provisionDigitalOutputPin(leftWheelsBackwardPin)
    val rightWheelsForward = gpioController.provisionDigitalOutputPin(rightWheelsForwardPin)
    val rightWheelsBackward = gpioController.provisionDigitalOutputPin(rightWheelsBackwardPin)
    val allWheelPins = arrayOf(leftWheelsForward, leftWheelsBackward, rightWheelsForward, rightWheelsBackward)
    /*val leftWheels = arrayOf(lfWheel, lbWheel)
    val rightWheels = arrayOf(rfWheel, rbWheel)
    val allWheels = leftWheels + rightWheels*/

    override fun sendData(outputData: WheelsControl) {
        print("Direction received: ${outputData.direction}")
        val duration = 100L
        when (outputData.direction.toByte()) {
            GO_FORWARD -> goForward(duration)
            GO_BACK -> goBack(duration)
            TURN_LEFT -> turnLeft(duration)
            TURN_RIGHT -> turnRight(duration)
            else -> throw IllegalArgumentException("illegal direction ${outputData.direction.toByte()}")
        }
    }

    fun move(wheelsControl: WheelsControl) = sendData(wheelsControl)

    fun goForward(duration: Long) {
        leftWheelsForward.pulse(duration)
        rightWheelsForward.pulse(duration)
    }

    fun goBack(duration: Long) {
        leftWheelsBackward.pulse(duration)
        rightWheelsBackward.pulse(duration)
    }

    fun turnLeft(duration: Long) {
        leftWheelsBackward.pulse(duration)
        rightWheelsForward.pulse(duration)
    }

    fun turnRight(duration: Long) {
        leftWheelsForward.pulse(duration)
        rightWheelsBackward.pulse(duration)
    }
}