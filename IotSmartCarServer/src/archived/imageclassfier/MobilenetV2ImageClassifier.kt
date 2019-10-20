package shreckye.iotsmartcar.imageclassfier

import org.tensorflow.Graph
import org.tensorflow.SavedModelBundle
import org.tensorflow.Session

class MobilenetV2ImageClassifier {
    val graph = Graph()
    init {
        val model = SavedModelBundle.load(ClassLoader.getSystemResource("mobilenet_v2_1.0_224").path, "mobilenet_v2_1.0_224")
        model.session().runner().fetch("").run()[0]
        //graph.importGraphDef()
    }
    val session = Session(graph)

    fun close() {
        session.close()
        graph.close()
    }
}