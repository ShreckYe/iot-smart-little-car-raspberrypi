package shreckye.iotsmartcar.device

interface AsyncActiveInputDevice<InputData> : ActiveInputDevice<InputData> {
    var inputListener: ((InputData) -> Unit)?
}