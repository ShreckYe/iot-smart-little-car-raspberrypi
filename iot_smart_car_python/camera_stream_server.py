import io
import socket
import struct
from PIL import Image
from picamera import PiCamera
from image_classifier import *

ACTION_IMAGE = 1
ACTION_RECOGNITION_PREDICTIONS = 2

STREAM_SERVICE_DEFAULT_PORT = 8002
DEFAULT_RESOLUTION = (640, 480)

print("Iot Smart Car camera stream server Python implementation\nYongshun Ye, 20190122")
server_socket = socket.socket()
server_socket.bind(('0.0.0.0', STREAM_SERVICE_DEFAULT_PORT))
server_socket.listen(0)
while True:
    try:
        connection = server_socket.accept()[0].makefile('wb')
        print("Client connected: {}".format(connection))
        with PiCamera() as camera:
            camera.resolution = DEFAULT_RESOLUTION
            camera.hflip = True
            camera.vflip = True

            image_stream = io.BytesIO()
            buffer_stream = io.BytesIO()
            for _ in camera.capture_continuous(image_stream, "jpeg"):
                # Writes the JPEG stream
                connection.write(struct.pack('>b', ACTION_IMAGE))

                connection.write(struct.pack('>i', image_stream.tell()))
                image_stream.seek(0)
                connection.write(image_stream.read())

                # Writes the predictions
                image_stream.seek(0)
                img = Image.open(image_stream)
                img = img.resize((224, 224))
                predictions = predict(img)

                connection.write(struct.pack('>b', ACTION_RECOGNITION_PREDICTIONS))

                buffer_stream.write(struct.pack('>b', len(predictions)))
                for prediction_tag, probability in predictions:
                    buffer_stream.write(struct.pack('>b', len(prediction_tag)))
                    buffer_stream.write(prediction_tag.encode())
                    buffer_stream.write(struct.pack('>f', probability))

                connection.write(struct.pack('>i', buffer_stream.tell()))
                buffer_stream.seek(0)
                connection.write(buffer_stream.read())

                image_stream.seek(0)
                image_stream.truncate()
                buffer_stream.seek(0)
                buffer_stream.truncate()
    except (BrokenPipeError, ConnectionResetError) as e:
        print("Client disconnected.")
        print(e)
