package shreckye.iotsmartcar.data


const val GO_FORWARD: Byte = 0
const val GO_BACK: Byte = 1
const val TURN_LEFT: Byte = 2
const val TURN_RIGHT: Byte = 3

class WheelsControl(val direction: Byte)
