package shreckye.iotsmartcar.server.net.control

import shreckye.iotsmartcar.codec.proto.IotSmartCarProto

interface ControlService {
    fun serve(motionControl: IotSmartCarProto.MotionControl)
}