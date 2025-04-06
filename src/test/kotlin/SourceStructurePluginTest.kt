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

    @Test
    fun `output contains correct file paths`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SourceStructurePlugin::class.java)
        val task = project.tasks.getByName("sourceStructure") as SourceStructureTask

        val sampleFile = File(project.projectDir, "src/sample/Sample.kt").apply {
            parentFile.mkdirs()
            writeText("fun greet() = println(\"Hi\")")
        }
        project.buildDir.mkdirs()
        task.generateStructure()

        val outputFile = File(project.buildDir, "source-structure.txt")
        val outputContent = outputFile.readText()
        assertTrue(outputContent.contains(sampleFile.relativeTo(project.projectDir).toString()), "Output should include the relative path of the Kotlin file.")
    }

    @Test
    fun `output includes Kotlin file content`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SourceStructurePlugin::class.java)
        val task = project.tasks.getByName("sourceStructure") as SourceStructureTask

        val fileContent = "fun testFunc() = println(\"Test\")"
        File(project.projectDir, "src/code/Test.kt").apply {
            parentFile.mkdirs()
            writeText(fileContent)
        }
        project.buildDir.mkdirs()
        task.generateStructure()

        val outputFile = File(project.buildDir, "source-structure.txt")
        val outputContent = outputFile.readText()
        assertTrue(outputContent.contains(fileContent), "Output file should contain the actual contents of the Kotlin source file.")
    }

    @Test
    fun `handles empty source directory`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SourceStructurePlugin::class.java)
        val task = project.tasks.getByName("sourceStructure") as SourceStructureTask

        File(project.projectDir, "src").mkdirs() // no files inside

        project.buildDir.mkdirs()
        task.generateStructure()

        val outputFile = File(project.buildDir, "source-structure.txt")
        val content = outputFile.readText()

        assertTrue(content.isEmpty(), "Output file should be empty when no Kotlin files are found.")
    }

    @Test
    fun `handles multiple Kotlin files`() {
        val project = ProjectBuilder.builder().build()
        val task = project.tasks.create("sourceStructure", SourceStructureTask::class.java)

        File(project.projectDir, "src/one/File1.kt").apply {
            parentFile.mkdirs()
            writeText("fun one() = println(\"One\")")
        }
        File(project.projectDir, "src/two/File2.kt").apply {
            parentFile.mkdirs()
            writeText("fun two() = println(\"Two\")")
        }
        project.buildDir.mkdirs()
        task.generateStructure()

        val output = File(project.buildDir, "source-structure.txt").readText()
        assertTrue(output.contains("File1.kt") && output.contains("File2.kt"), "Both Kotlin files should be listed in the output.")
        assertTrue(output.contains("fun one()") && output.contains("fun two()"), "Both Kotlin file contents should be included.")
    }



}
