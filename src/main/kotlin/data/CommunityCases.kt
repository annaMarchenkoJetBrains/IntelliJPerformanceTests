package data

import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.ide.starter.project.RemoteArchiveProjectInfo
import com.intellij.ide.starter.project.TestCaseTemplate
import java.nio.file.Paths

import kotlin.io.path.div


object CommunityCases : TestCaseTemplate(IdeProductProvider.IC) {
    val CommunitySources = getTemplate().withProject(

        RemoteArchiveProjectInfo(
            projectURL = "https://github.com/JetBrains/intellij-community/archive/master.zip",
            projectHomeRelativePath = { it / "intellij-community-master" }
        )
    )

    val LocalProject = getTemplate().withProject(
        LocalProjectInfo(
            projectDir = Paths.get(System.getProperty("user.dir"), "projectsForTests/hello-world")
        )
    )

}

object UltimateCases : TestCaseTemplate(IdeProductProvider.IU) {

    val LocalProject = getTemplate().withProject(
        LocalProjectInfo(
            projectDir = Paths.get(System.getProperty("user.dir"), "projectsForTests/hello-world")
        )
    )

}


object TestCases {
    val IC = CommunityCases
    val IU = UltimateCases
}