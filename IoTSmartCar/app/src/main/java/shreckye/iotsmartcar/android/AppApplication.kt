package shreckye.iotsmartcar.android

import android.app.Application
import android.graphics.Bitmap
import shreckye.iotsmartcar.android.client.net.DefaultAndroidClient
import shreckye.iotsmartcar.data.RecognitionPrediction

class AppApplication : Application() {
    var client: DefaultAndroidClient? = null
    fun initClient(
        host: String,
        imageReceivedListener: (Bitmap) -> Unit,
        recognitionPredictionsReceivedListener: (List<RecognitionPrediction>) -> Unit
    ) {
        client = DefaultAndroidClient(host, imageReceivedListener, recognitionPredictionsReceivedListener)
    }

    fun closeClientIfNeeded() {
        client?.close()
    }
}