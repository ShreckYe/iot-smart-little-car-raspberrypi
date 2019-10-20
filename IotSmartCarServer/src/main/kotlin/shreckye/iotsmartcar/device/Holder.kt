package shreckye.iotsmartcar.device

import shreckye.iotsmartcar.data.HolderControl

class Holder : OutputDevice<HolderControl> {
    override val type: String = "holder"
    override fun sendData(outputData: HolderControl) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
