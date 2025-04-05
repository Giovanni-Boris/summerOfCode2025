import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinSourceStructurePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("sourceStructure", SourceStructureTask::class.java)
    }
}

