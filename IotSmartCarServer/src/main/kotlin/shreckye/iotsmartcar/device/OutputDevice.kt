package shreckye.iotsmartcar.device

interface OutputDevice<OutputData> : HardwareDevice {
    fun sendData(outputData: OutputData)
}