package shreckye.iotsmartcar.device

class InfraredDevice : PassiveInputDevice<Int>, OutputDevice<Int> {
    override val type: String = "infrared device"
    override fun retrieveInput(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendData(outputData: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}