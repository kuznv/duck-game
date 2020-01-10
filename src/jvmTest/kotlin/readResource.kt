import kotlinx.coroutines.runBlocking
import java.io.File
import java.net.URLDecoder

actual fun readResource(file: String): String {
    val path = URLDecoder.decode(file, Charsets.UTF_8)
    return File(ClassLoader.getSystemResource(path).toURI()).readText()
}