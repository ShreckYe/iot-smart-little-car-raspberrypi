package shreckye.iotsmartcar.server.net.reliable

import io.grpc.stub.StreamObserver
import shreckye.iotsmartcar.IotSmartCar
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.codec.proto.ReliableServiceGrpc

class GrpcReliableService(val iotSmartCar: IotSmartCar) : ReliableServiceGrpc.ReliableServiceImplBase() {
    override fun capture(request: IotSmartCarProto.CaptureRequest, responseObserver: StreamObserver<IotSmartCarProto.CaptureResponse>) {
        println("gRPC received: ${request::class}")
        //iotSmartCar.saveLatestImage()

        responseObserver.onNext(IotSmartCarProto.CaptureResponse.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun getPhotoList(request: IotSmartCarProto.GetPhotoListRequest, responseObserver: StreamObserver<IotSmartCarProto.PhotoItem>) {
        println("gRPC received: ${request::class}")
        for (filename in shreckye.iotsmartcar.server.content.getPhotoFilenames()) {
            println("Sending: $filename")
            responseObserver.onNext(IotSmartCarProto.PhotoItem.newBuilder().setFilename(filename).build())
        }
        responseObserver.onCompleted()
    }
}