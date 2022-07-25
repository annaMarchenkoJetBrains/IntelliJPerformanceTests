import com.intellij.ide.starter.downloadAndroidPluginProject
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.ide.starter.ide.command.CommandChain
import com.intellij.metricsCollector.metrics.extractIndexingMetrics
import com.intellij.metricsCollector.publishing.publishIndexingMetrics
import com.jetbrains.performancePlugin.commands.chain.exitApp
import data.TestCases
import junit4.initStarterRule
import junit4.toPrintableWithClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import kotlin.time.Duration.Companion.minutes

class IntegrationPerformanceTests {
    @get:Rule
    val testName = TestName()

    @get:Rule
    val testContextFactory = initStarterRule()

    val downloadPerformancePlugin: IDETestContext.() -> IDETestContext = {
        pluginConfigurator.setupPluginFromPluginManager("com.jetbrains.performancePlugin", ideBuild = this.ide.build)
        this
    }


    @Test
    fun communitySourcesIndexing() {
        val context = testContextFactory
            .initializeTestRunner(testName.toPrintableWithClass(this::class), TestCases.IC.CommunitySources)
            .downloadAndroidPluginProject()
            .downloadPerformancePlugin()

        val result = context
            .setMemorySize(2 * 1024)
            .runIDE(
                commands = CommandChain().exitApp(),
                runTimeout = 20.minutes
            )
        extractIndexingMetrics(result).publishIndexingMetrics()
    }

    @Test
    fun helloWorldIndexing() {
        val context = testContextFactory
            .initializeTestRunner(testName.toPrintableWithClass(this::class), TestCases.IC.LocalProject)
            .downloadPerformancePlugin()

        val result = context
            .runIDE(
                commands = CommandChain().exitApp(),
                runTimeout = 5.minutes
            )
        extractIndexingMetrics(result).publishIndexingMetrics()
    }
}