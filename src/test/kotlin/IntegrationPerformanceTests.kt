import com.intellij.ide.starter.downloadAndroidPluginProject
import com.intellij.ide.starter.ide.IDETestContext
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

        context.runIDE(
            commands = CommandChain().exitApp()
        )


    }
}