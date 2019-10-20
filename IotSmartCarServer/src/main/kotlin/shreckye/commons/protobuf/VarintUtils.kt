package shreckye.commons.protobuf

import com.google.protobuf.CodedInputStream
import com.google.protobuf.CodedOutputStream
import java.io.InputStream
import java.io.OutputStream

fun InputStream.readVarint32(): Int =
        CodedInputStream.newInstance(this).readInt32()

fun OutputStream.writeVarint32(value: Int) =
        CodedOutputStream.newInstance(this).writeInt32NoTag(value)