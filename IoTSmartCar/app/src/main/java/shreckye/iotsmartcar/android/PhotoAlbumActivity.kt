package shreckye.iotsmartcar.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_photo_album.*

class PhotoAlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_album)

        photo_grid.layoutManager = GridLayoutManager(this,3)
        photo_grid.adapter = PhotoGridAdapter(this, (application as AppApplication).client!!)
    }
}
