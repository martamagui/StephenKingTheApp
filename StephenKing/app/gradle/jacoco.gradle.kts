import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.tasks.JacocoReport


tasks.register<JacocoReport>("jacocoUnitTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "jdk.internal.*",
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/com/mmag/stephenking/*StephenKingApplication**",
        "**/com/mmag/stephenking/data/network/model/**",
        "**/com/mmag/stephenking/domain/model/**",
        "**/com/mmag/stephenking/domain/model/mapper/**",
        "**/dagger/hilt/internal/aggregatedroot/codegen/**",
        "**/hilt_aggregated_deps/**",
        "**/com/mmag/stephenking/ui/**/*Screen*",
        "**/com/mmag/stephenking/ui/**/*Activity*",
        "**/com/mmag/stephenking/ui/theme/**",
        "**/com/mmag/stephenking/ui/commonComponents/**",
        "**/com/mmag/stephenking/ui/navigation/**",
    )

    val kotlinDebugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(kotlinDebugTree))

    executionData.setFrom(fileTree(buildDir) {
        include("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",)
    })
}
