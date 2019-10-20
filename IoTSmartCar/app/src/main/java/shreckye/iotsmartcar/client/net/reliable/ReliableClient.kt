package shreckye.iotsmartcar.client.net.reliable

import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.codec.proto.ReliableServiceGrpc
import shreckye.iotsmartcar.net.reliable.ReliableServiceConstants
import java.io.Closeable

class ReliableClient(val host: String, val port: Int = ReliableServiceConstants.DEFAULT_PORT) : Closeable {
    val channel = ManagedChannelBuilder
        .forAddress(host, port)
        .usePlaintext()
        .build()
    val asyncStub = ReliableServiceGrpc.newStub(channel)
    val blockingStub = ReliableServiceGrpc.newBlockingStub(channel)

    override fun close() {
        channel.shutdown()
    }

    fun capture(
        reqeust: IotSmartCarProto.CaptureRequest,
        responseObserver: StreamObserver<IotSmartCarProto.CaptureResponse>
    ) = asyncStub.capture(reqeust, responseObserver)

    fun getPhotoList(
        request: IotSmartCarProto.GetPhotoListRequest,
        responseObserver: StreamObserver<IotSmartCarProto.PhotoItem>
    ) = asyncStub.getPhotoList(request, responseObserver)

    /*init {
        asyncStub.capture(IotSmartCarProto.CaptureRequest.getDefaultInstance(),
                object : StreamObserver<IotSmartCarProto.CaptureResponse> {
                    override fun onNext(value: IotSmartCarProto.CaptureResponse?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onCompleted() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
        blockingStub.capture(IotSmartCarProto.CaptureRequest.getDefaultInstance())
    }*/
}