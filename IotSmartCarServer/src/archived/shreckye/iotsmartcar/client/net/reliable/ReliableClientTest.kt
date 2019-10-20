package shreckye.iotsmartcar.client.net.reliable

import io.grpc.stub.StreamObserver
import org.junit.Test
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import java.util.concurrent.CountDownLatch

class ReliableClientTest {
    @Test
    fun testReliableClientAsync() {
        val countDownLatch = CountDownLatch(1)
        ReliableClient("localhost").use {
            it.capture(
                    IotSmartCarProto.CaptureRequest.getDefaultInstance(),
                    object : StreamObserver<IotSmartCarProto.CaptureResponse> {
                        override fun onNext(value: IotSmartCarProto.CaptureResponse) {
                            println("Response")
                        }

                        override fun onError(t: Throwable) {
                            t.printStackTrace()
                        }

                        override fun onCompleted() {
                            println("Completed")
                            countDownLatch.countDown()
                        }
                    })
            countDownLatch.await()
        }
    }

    @Test
    fun testReliableClientBlocking() {
        ReliableClient("localhost").use {
            val response = it.blockingStub.capture(IotSmartCarProto.CaptureRequest.getDefaultInstance())
            println("Captured: $response")
        }
    }
}