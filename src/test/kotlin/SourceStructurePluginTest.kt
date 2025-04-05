import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class SourceStructurePluginTest {
    @Test
    fun `plugin applies correctly`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SourceStructurePlugin::class.java)

        assertTrue(project.tasks.findByName("sourceStructure") != null, "Task should be registered")
    }

    @Test
    fun `task generates output file`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SourceStructurePlugin::class.java)
        val task = project.tasks.getByName("sourceStructure") as SourceStructureTask

        project.buildDir.mkdirs()
        task.generateStructure()
        val outputFile = File(project.buildDir, "source-structure.txt")

        assertTrue(outputFile.exists(), "Output file should exist after task execution")
    }
}
