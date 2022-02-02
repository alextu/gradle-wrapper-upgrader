import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import org.gradle.work.DisableCachingByDefault

import javax.inject.Inject

@DisableCachingByDefault(because = 'Produces no cacheable output')
abstract class UpgradeWrapper extends DefaultTask {

    @Inject
    abstract ExecOperations getExecOperations()

    @Input
    abstract DirectoryProperty getGradleProject()

    @Input
    abstract Property<String> getGradleVersion()

    @TaskAction
    def upgradeWrapper() {
        execOperations.exec { execSpec ->
            def args = ['./gradlew', 'wrapper', '--gradle-version', gradleVersion.get()]
            execSpec.workingDir(gradleProject.get())
            execSpec.commandLine(args)
        }
        execOperations.exec { execSpec ->
            def args = ['./gradlew', 'wrapper', '--gradle-version', gradleVersion.get()]
            execSpec.workingDir(gradleProject.get())
            execSpec.commandLine(args)
        }
    }
}
