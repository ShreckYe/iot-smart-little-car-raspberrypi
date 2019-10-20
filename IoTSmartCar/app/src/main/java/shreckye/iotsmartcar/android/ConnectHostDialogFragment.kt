package shreckye.iotsmartcar.android

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class ConnectHostDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val editText = EditText(context!!)
        editText.setHint(R.string.a_host_address)
        editText.setText("192.168.1.1")
        //editText.layoutParams = FrameLayout.LayoutParams()
        val dialog = AlertDialog.Builder(context!!)
            .setTitle(R.string.connect_to_a_host)
            .setView(editText)
            .setPositiveButton(R.string.connect, null)
            .setNegativeButton(R.string.close) { _, _ ->
                closeApp()
            }
            /*.setCancelable(false)
            .setOnCancelListener {
                activity!!.finish()
            }*/
            .setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    closeApp()
                    true
                } else
                    false
            }
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                try {
                    val mainActivity = activity!! as MainActivity
                    (activity!!.application as AppApplication).initClient(editText.text.toString(), {
                        with(mainActivity) {
                            runOnUiThread {
                                camera_view.setImageBitmap(it)
                            }
                        }
                    }, {
                        val stringBuilder = StringBuilder()
                        for (recognitionPrediction in it)
                            stringBuilder
                                .append(recognitionPrediction.tag)
                                .append(": ")
                                .append((recognitionPrediction.probability * 100).toInt())
                                .append("%\n")
                        val string = stringBuilder.toString()
                        Log.d("predictions", string)
                        with(mainActivity) {
                            runOnUiThread {
                                info_text.text = string
                            }
                        }
                    })
                    /*(activity!!.application as AppApplication).doAsync {
                        weakRef.get()!!.initClient(editText.text.toString())
                    }.get()*/
                    dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                    activity!!.longToast(getString(R.string.cannot_connect_message) + "\n" + e)
                }
            }
        }

        return dialog
    }

    private fun closeApp() {
        activity!!.finish()
    }
}