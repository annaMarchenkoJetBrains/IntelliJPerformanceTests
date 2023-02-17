import com.intellij.ide.starter.downloadAndroidPluginProject
import com.intellij.ide.starter.ide.command.CommandChain
import com.intellij.metricsCollector.metrics.extractIndexingMetrics
import com.intellij.metricsCollector.publishing.publishIndexingMetrics
import com.jetbrains.performancePlugin.commands.chain.*
import data.TestCases
import junit4.initStarterRule
import junit4.toPrintableWithClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import java.nio.file.Paths
import kotlin.time.Duration.Companion.minutes

class IntegrationPerformanceTests {
    @get:Rule
    val testName = TestName()

    @get:Rule
    val testContextFactory = initStarterRule()


    @Test
    fun communitySourcesIndexing() {
        val context = testContextFactory
            .initializeTestContext(testName.toPrintableWithClass(this::class), TestCases.IC.CommunitySources)
            .downloadAndroidPluginProject()

        val result = context
            .setMemorySize(2 * 1024)
            .runIDE(
                commands = CommandChain().exitApp(),
                runTimeout = 20.minutes
            )
        extractIndexingMetrics(result).publishIndexingMetrics()
    }

    @Test
    fun profilingIndexingHelloWorld() {
        val context = testContextFactory
            .initializeTestContext(testName.toPrintableWithClass(this::class), TestCases.IC.LocalProject)

        val result = context
            .setPathForSnapshots()
            .runIDE(
                commands = CommandChain()
                    .profileIndexing(testName.toPrintableWithClass(this::class))
                    .waitForSmartMode()
                    .stopProfile()
                    .exitApp(),
                runTimeout = 5.minutes
            )
        extractIndexingMetrics(result).publishIndexingMetrics()
    }

    @Test
    fun indexingUltimateIntelliJ() {
        val context = testContextFactory
            .initializeTestContext(testName.toPrintableWithClass(this::class), TestCases.IU.LocalProject)
                //set license for IU IDE
            .setLicense(Paths.get(System.getProperty("user.dir"), "out/license/idea.key"))

        val result = context
            .runIDE(
                commands = CommandChain(),
                runTimeout = 5.minutes
            )
        extractIndexingMetrics(result).publishIndexingMetrics()
    }
}