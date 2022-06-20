package junit4
import org.junit.rules.TestName
import java.util.*
import kotlin.reflect.KClass

fun TestName.toPrintableWithClass(clazz: KClass<*>): String {
    return "${clazz.simpleName}/${this.methodName}".toPrintableTestName()
}

fun String.toPrintableTestName(): String {
    return this.replace(" ", "-").trim().replaceFirstChar { it.lowercase(Locale.getDefault()) }.toCharArray()
        .map {
            if (it.isUpperCase()) "-${it.lowercaseChar()}"
            else it
        }
        .joinToString(separator = "")
}