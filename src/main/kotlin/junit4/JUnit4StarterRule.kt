package junit4

import com.intellij.ide.starter.bus.StarterListener
import com.intellij.ide.starter.ci.CIServer
import com.intellij.ide.starter.ci.NoCIServer
import com.intellij.ide.starter.config.ConfigurationStorage
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.ide.starter.process.killOutdatedProcessesOnUnix
import com.intellij.ide.starter.runner.TestContainer
import com.intellij.ide.starter.system.SystemInfo
import com.intellij.ide.starter.utils.catchAll
import org.junit.rules.ExternalResource
import org.junit.runner.Description
import org.junit.runners.model.Statement

fun initStarterRule(): JUnit4StarterRule = JUnit4StarterRule()

open class JUnit4StarterRule(
    override val setupHooks: MutableList<IDETestContext.() -> IDETestContext> = mutableListOf(),
    override val ciServer: CIServer = NoCIServer

) : ExternalResource(), TestContainer<JUnit4StarterRule> {

    override lateinit var testContext: IDETestContext

    private lateinit var testDescription: Description

    override fun apply(base: Statement, description: Description): Statement {
        testDescription = description
        return super.apply(base, description)
    }

    override fun before() {
        if (!SystemInfo.isWindows) {
            killOutdatedProcessesOnUnix()
        }
    }

    override fun close() {
        catchAll { testContext.paths.close() }
    }

    override fun after() {
        StarterListener.unsubscribe()
        close()
        ConfigurationStorage.instance().resetToDefault()
        super.after()
    }
}