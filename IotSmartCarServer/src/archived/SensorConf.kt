package shreckye.iotsmartcar.sensor

class SensorConf<Sensor : shreckye.iotsmartcar.sensor.HardwareDevice>(
        val id: Int,
        var name: String,
        val sensor: Sensor
)