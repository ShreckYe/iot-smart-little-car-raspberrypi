package shreckye.iotsmartcar.android

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.grpc.stub.StreamObserver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.control_buttons.*
import org.jetbrains.anko.toast
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import shreckye.iotsmartcar.data.GO_BACK
import shreckye.iotsmartcar.data.GO_FORWARD
import shreckye.iotsmartcar.data.TURN_LEFT
import shreckye.iotsmartcar.data.TURN_RIGHT
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    var controlExecutor: ExecutorService? = null

    inner class DirectionButtonOnTouchListener(private val direction: Byte) : View.OnTouchListener {
        var executorFuture: Future<*>? = null
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    try {
                        executorFuture = controlExecutor!!.submit {
                            while (!Thread.interrupted()) {
                                (application as AppApplication).client!!.sendControl(
                                    IotSmartCarProto.MotionControl.newBuilder().setWheelsControl(
                                        IotSmartCarProto.WheelsControl.newBuilder().setDirection(direction.toInt())
                                    ).build()
                                )

                                Thread.sleep(80)
                            }
                        }
                    } catch (t: Throwable) {
                        t.printStackTrace()
                    }

                    return true
                }
                MotionEvent.ACTION_UP -> {
                    executorFuture!!.cancel(true)

                    return true
                }
                else ->
                    return false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ConnectHostDialogFragment().show(supportFragmentManager, null)

        controlExecutor = Executors.newSingleThreadExecutor()
        up_button.setOnTouchListener(DirectionButtonOnTouchListener(GO_FORWARD))
        left_button.setOnTouchListener(DirectionButtonOnTouchListener(TURN_LEFT))
        right_button.setOnTouchListener(DirectionButtonOnTouchListener(TURN_RIGHT))
        down_button.setOnTouchListener(DirectionButtonOnTouchListener(GO_BACK))

        capture_button.setOnClickListener {
            (application as AppApplication).client!!.capture(
                IotSmartCarProto.CaptureRequest.getDefaultInstance(),
                object : StreamObserver<IotSmartCarProto.CaptureResponse> {
                    override fun onNext(value: IotSmartCarProto.CaptureResponse?) {}
                    override fun onError(t: Throwable?) {}
                    override fun onCompleted() {
                        toast(R.string.photo_captured)
                    }
                })
        }

        photo_album_button.setOnClickListener { startActivity(Intent(this, PhotoAlbumActivity::class.java)) }
    }

    override fun onDestroy() {
        controlExecutor!!.shutdown()

        super.onDestroy()
    }
}
