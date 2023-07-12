package com.example.jacksonyamlproblem

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class JacksonYamlProblemApplicationTests {

    val invalidFile = File(javaClass.classLoader.getResource("invalid.yml")?.file
            ?: throw RuntimeException())

    val yamlMapper = YAMLMapper().apply {
        registerKotlinModule()
    }

    @Test
    fun contextLoads() {
    }

    /**
     * In both cases, two problems come up:
     *
     * First: The yaml is breaking general yaml rules: It has a duplicated key
     *
     * Second: Only block "b" gets into this result, the "a" block gets ignored (probably due to First)
     *
     * Why are there no errors? For instance, like with the Spring YAML config here:
     * https://stackoverflow.com/questions/74704616/found-duplicate-key-in-application-yml
     */

    @Test
    fun `yaml into jsonNode`() {
        val jsonNode = yamlMapper.readTree(invalidFile)

        val breakpoint = 0
    }

    @Test
    fun `yaml into object`() {
        val result = yamlMapper.readValue<ObjectDto>(invalidFile.readText())

        val breakpoint = 0
    }

}
