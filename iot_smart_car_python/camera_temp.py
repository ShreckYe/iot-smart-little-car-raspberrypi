import io
from picamera import PiCamera

DEFAULT_RESOLUTION = (640, 480)

with PiCamera() as camera:
    camera.resolution = DEFAULT_RESOLUTION
    camera.hflip = True
    camera.vflip = True

    image_stream = io.BytesIO()
    for _ in camera.capture_continuous(image_stream, "jpeg"):
        ...
        image_stream.seek(0)
        image_stream.truncate()
