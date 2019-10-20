package shreckye.iotsmartcar.device

interface SyncActiveInputDevice<InputData> : ActiveInputDevice<InputData> {
    fun awaitInput(): InputData
}