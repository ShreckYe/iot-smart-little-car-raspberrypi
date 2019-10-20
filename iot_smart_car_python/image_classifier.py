from tensorflow.keras.applications.mobilenet_v2 import MobileNetV2, preprocess_input
from tensorflow.keras.preprocessing import image
import numpy as np
import json

model = MobileNetV2(weights="mobilenet_v2_weights_tf_dim_ordering_tf_kernels_1.0_224.h5")
# Init all weights
model.predict(np.zeros((1, 224, 224, 3)))
with open("imagenet_class_index.json") as file:
    prediction_json_dict = json.load(file)
prediction_tags = [value[1] for key, value in prediction_json_dict.items()]


def preprocess(img):
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = preprocess_input(x)
    return x


def decode_prediction(prediction, top=5):
    top_indices = prediction.argsort()[:-(top + 1):-1]
    return [(prediction_tags[i], prediction[i]) for i in top_indices]


def predict(img):
    x = preprocess(img)
    predictions = model.predict(x)[0]
    return decode_prediction(predictions)
