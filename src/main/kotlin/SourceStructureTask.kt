import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class SourceStructureTask : org.gradle.api.DefaultTask() {
    @TaskAction
    fun generateStructure() {
        val filesSourceDirs = project.fileTree("src").matching {
            include("**/*.kt", "**/*.kts")
        }.files

        val outputFile = File(project.buildDir, "source-structure.txt")
        outputFile.bufferedWriter().use { writer ->
            filesSourceDirs.forEach { file ->
                writer.write("File in current project: ${file.relativeTo(project.projectDir)}\n")
                writer.write("----------------------------\n")
                writer.write(file.readText())
                writer.write("\n\n")
            }
        }
        println("Plugin generated source structure written to: ${outputFile.absolutePath}")
    }
}