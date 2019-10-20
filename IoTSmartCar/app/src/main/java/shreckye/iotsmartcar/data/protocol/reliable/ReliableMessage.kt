package shreckye.iotsmartcar.data.protocol.reliable

interface ReliableMessage<Action, Data> {
    val action: Action
    val data: Data
}