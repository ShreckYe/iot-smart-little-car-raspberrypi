package shreckye.iotsmartcar.android.client.net

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import shreckye.iotsmartcar.client.net.AbstractDefaultCRSClient
import shreckye.iotsmartcar.data.RecognitionPrediction
import java.io.InputStream

class DefaultAndroidClient(
    host: String,
    imageReceivedListener: (Bitmap) -> Unit,
    recognitionPredictionsReceivedListener: (List<RecognitionPrediction>) -> Unit = {}
) :
    AbstractDefaultCRSClient<Bitmap>(host, imageReceivedListener, recognitionPredictionsReceivedListener) {
    override fun readJpegStream(`in`: InputStream): Bitmap =
        BitmapFactory.decodeStream(`in`)

    fun loadPhoto(imageView: ImageView, filename: String) {
        Glide.with(imageView.context)
            .load(getPhotoUrl(filename))
            .into(imageView)
    }
}