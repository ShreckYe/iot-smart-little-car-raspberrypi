package shreckye.iotsmartcar.device

class TemperatureHumidityLightSensor : PassiveInputDevice<IntArray> {
    override val type: String = "temperature, humidity, and light device"
    override fun retrieveInput(): IntArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}