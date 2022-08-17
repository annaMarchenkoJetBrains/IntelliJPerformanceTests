package data

import com.intellij.ide.starter.data.TestCaseTemplate
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.project.ProjectInfo
import java.nio.file.Paths

import kotlin.io.path.div


object CommunityCases : TestCaseTemplate(IdeProductProvider.IC) {
    val CommunitySources = getTemplate().withProject(
        ProjectInfo(
            testProjectURL = "https://github.com/JetBrains/intellij-community/archive/master.zip",
            testProjectImageRelPath = { it / "intellij-community-master" }
        )
    )

    val LocalProject = getTemplate().withProject(
        ProjectInfo(
            testProjectDir = Paths.get(System.getProperty("user.dir"), "projectsForTests/hello-world")
        )
    )

}

object UltimateCases : TestCaseTemplate(IdeProductProvider.IU) {

    val LocalProject = getTemplate().withProject(
        ProjectInfo(
            testProjectDir = Paths.get(System.getProperty("user.dir"), "projectsForTests/hello-world")
        )
    )

}


object TestCases {
    val IC = CommunityCases
    val IU = UltimateCases
}