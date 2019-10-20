package shreckye.iotsmartcar.client.net.control

import org.junit.Test
import shreckye.iotsmartcar.client.net.control.ControlClient
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.data.TURN_LEFT

class ControlClientTest {
    @Test
    fun testControlClient() {
        ControlClient("localhost").use {
            it.sendControlSync(
                    IotSmartCarProto.MotionControl.newBuilder().setWheelsControl(
                            IotSmartCarProto.WheelsControl.newBuilder().setDirection(TURN_LEFT.toInt())
                    ).build())
            println("Sent")
        }
    }
}