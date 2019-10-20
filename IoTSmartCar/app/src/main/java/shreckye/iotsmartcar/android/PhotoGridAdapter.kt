package shreckye.iotsmartcar.android

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.grpc.stub.StreamObserver
import org.jetbrains.anko.toast
import shreckye.iotsmartcar.android.client.net.DefaultAndroidClient
import shreckye.iotsmartcar.codec.proto.IotSmartCarProto
import java.util.*

class PhotoGridAdapter(private val activity: Activity, private val client: DefaultAndroidClient) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PhotoGridAdapter.ViewHolder>() {
    val photos: Vector<String> = Vector()

    init {
        refresh()
    }

    fun refresh() {
        photos.clear()
        notifyDataSetChanged()
        client.getPhotoList(
            IotSmartCarProto.GetPhotoListRequest.getDefaultInstance(),
            object : StreamObserver<IotSmartCarProto.PhotoItem> {
                override fun onNext(value: IotSmartCarProto.PhotoItem) {
                    val index = photos.size
                    photos.add(value.filename)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }

                override fun onCompleted() {
                    activity.runOnUiThread { notifyDataSetChanged() }
                }
            })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(activity).inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setPhoto(photos[position])
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val photoView: ImageView = itemView.findViewById(R.id.photo_view)
        val photoNameView: TextView = itemView.findViewById(R.id.photo_name_view)
        fun setPhoto(photoName: String) {
            client.loadPhoto(photoView, photoName)
            activity.toast(photoName)
            photoNameView.setText(photoName)
        }
    }
}