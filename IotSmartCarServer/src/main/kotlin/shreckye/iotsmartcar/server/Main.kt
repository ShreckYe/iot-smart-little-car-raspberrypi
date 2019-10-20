package shreckye.iotsmartcar.server

fun main() {
    println("IoT Smart Car Server by Yongshun Ye")

    println("Server starting...")
    DefaultServer().use {
        println("Server started.")
        println("Enter \"exit\" to exit.")
        loop@ while (true) {
            val string = readLine()!!
            when (string) {
                "exit" -> break@loop
                else -> println("Invalid input.")
            }
        }

    }

}