package shreckye.iotsmartcar.device

interface OnOffDevice : HardwareDevice {
    fun on()
    fun off()
}