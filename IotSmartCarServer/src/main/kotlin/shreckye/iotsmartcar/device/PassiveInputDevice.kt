package shreckye.iotsmartcar.device

interface PassiveInputDevice<InputData> : InputDevice<InputData> {
    fun retrieveInput(): InputData
}