package shreckye.iotsmartcar.device

interface ComprehensiveInputDevice<InputData> :
        InputDevice<InputData>,
        ComprehensiveActiveInputDevice<InputData>,
        PassiveInputDevice<InputData>