package data

import com.intellij.ide.starter.data.TestCaseTemplate
import com.intellij.ide.starter.models.IdeProduct
import com.intellij.ide.starter.project.ProjectInfo

import kotlin.io.path.div


object IntelliJCases : TestCaseTemplate(IdeProduct.IC) {
    val CommunitySources = getTemplate().withProject(
        ProjectInfo(
            testProjectURL = "https://github.com/JetBrains/intellij-community/archive/master.zip",
            testProjectImageRelPath = { it / "intellij-community-master" }
        )
    )
}

object TestCases {
    val IC = IntelliJCases
}