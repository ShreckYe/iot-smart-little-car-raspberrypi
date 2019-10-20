package shreckye.iotsmartcar.python


class PythonCameraStreamServer : AutoCloseable {
    val process = Runtime.getRuntime().exec("python3 ", arrayOf(
            ClassLoader.getSystemResource("camera_stream_server.py").path))

    override fun close() = process.destroy()
}