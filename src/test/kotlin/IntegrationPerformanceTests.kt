import com.intellij.ide.starter.downloadAndroidPluginProject
import com.intellij.ide.starter.ide.command.CommandChain
import com.jetbrains.performancePlugin.commands.chain.exitApp
import data.TestCases
import junit4.initStarterRule
import junit4.toPrintableWithClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

class IntegrationPerformanceTests {
    @get:Rule
    val testName = TestName()

    @get:Rule
    val testContextFactory = initStarterRule()

    @Test
    fun communitySourcesIndexing() {
        val context = testContextFactory
            .initializeTestRunner(testName.toPrintableWithClass(this::class), TestCases.IJ.IntelliJCommunityProject)
            .downloadAndroidPluginProject()
        context.runIDE(
            commands = CommandChain().exitApp()
        )

    }

    @Test
    fun checkStringSubstirng() {
        val line = "Completion execution time = 1"
        val regex = "Completion execution time = "
        println(line.substringAfter(regex).substringBefore(" ms").trim())
    }
}