package data

import com.intellij.ide.starter.data.TestCaseTemplate
import com.intellij.ide.starter.models.IdeProduct
import com.intellij.ide.starter.project.ProjectInfo
import java.nio.file.Paths

import kotlin.io.path.div


object IntelliJCases : TestCaseTemplate(IdeProduct.IC) {
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

object TestCases {
    val IC = IntelliJCases
}