package shreckye.iotsmartcar.server.net.reliable

import io.grpc.Server
import io.grpc.ServerBuilder
import shreckye.iotsmartcar.IotSmartCar
import shreckye.iotsmartcar.net.reliable.ReliableServiceConstants

class ReliableServer(iotSmartCar: IotSmartCar, val port: Int = ReliableServiceConstants.DEFAULT_PORT) {
    val grpcServer: Server = ServerBuilder
            .forPort(port)
            .addService(GrpcReliableService(iotSmartCar))
            .build()
            .start()

    //fun start() = grpcServer.start()
    fun close() = grpcServer.shutdown()
}