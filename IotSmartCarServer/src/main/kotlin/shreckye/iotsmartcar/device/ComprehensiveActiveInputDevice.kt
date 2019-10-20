package shreckye.iotsmartcar.device

interface ComprehensiveActiveInputDevice<InputData> :
        ActiveInputDevice<InputData>,
        SyncActiveInputDevice<InputData>,
        AsyncActiveInputDevice<InputData>