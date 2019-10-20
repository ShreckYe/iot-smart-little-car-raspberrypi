package shreckye.iotsmartcar.client.net

import io.grpc.stub.StreamObserver
import shreckye.iotsmartcar.client.net.control.ControlClient
import shreckye.iotsmartcar.client.net.reliable.ReliableClient
import shreckye.iotsmartcar.client.net.stream.AbstractStreamClient
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto.MotionControl
import shreckye.iotsmartcar.data.RecognitionPrediction
import shreckye.iotsmartcar.net.content.getPhotoUrl
import java.io.InputStream

abstract class AbstractDefaultCRSClient<Image>(
    val host: String,
    imageReceivedListener: (Image) -> Unit = {},
    recognitionPredictionsReceivedListener: (List<RecognitionPrediction>) -> Unit = {}
) {
    val controlClient = ControlClient(host)
    val reliableClient = ReliableClient(host)
    abstract fun readJpegStream(`in`: InputStream): Image
    val streamClient =
        object : AbstractStreamClient<Image>(imageReceivedListener, recognitionPredictionsReceivedListener, host) {
            override fun readJpegStream(`in`: InputStream): Image = this@AbstractDefaultCRSClient.readJpegStream(`in`)
        }

    fun close() {
        controlClient.close()
        reliableClient.close()
        streamClient.close()
    }

    fun sendControl(motionControl: MotionControl) = controlClient.sendControl(motionControl)
    fun capture(
        reqeust: IotSmartCarProto.CaptureRequest,
        responseObserver: StreamObserver<IotSmartCarProto.CaptureResponse>
    ) = reliableClient.capture(reqeust, responseObserver)

    fun getPhotoList(
        request: IotSmartCarProto.GetPhotoListRequest,
        responseObserver: StreamObserver<IotSmartCarProto.PhotoItem>
    ) = reliableClient.getPhotoList(request, responseObserver)

    fun getPhotoUrl(filename: String) = getPhotoUrl(host = host, filename = filename)
}