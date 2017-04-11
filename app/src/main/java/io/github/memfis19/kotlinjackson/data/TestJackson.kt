package io.github.memfis19.kotlinjackson.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.memfis19.kotlinjackson.models.Check
import io.github.memfis19.kotlinjackson.models.IPolymorphic
import io.github.memfis19.kotlinjackson.models.Fake
import io.github.memfis19.kotlinjackson.models.helper.PolymorphicJsonDeserializer
import io.github.memfis19.kotlinjackson.models.helper.PolymorphicTypeRegistry

/**
 * Created by Rodion on 11.04.2017.
 */
class TestJackson {

    private var objectMapper: ObjectMapper? = null

    init {
        PolymorphicTypeRegistry.instance.add(Check.ModelTypeName, Check::class.java)
        PolymorphicTypeRegistry.instance.add(Fake.ModelTypeName, Fake::class.java)

        val outModule: SimpleModule = SimpleModule()
        outModule.addDeserializer(IPolymorphic::class.java, PolymorphicJsonDeserializer(object : PolymorphicJsonDeserializer.ModuleRegistrationHandler {
            override fun handleRegistration(module: SimpleModule) {
                //skip for now
            }
        }))

        objectMapper = ObjectMapper().registerKotlinModule()
        objectMapper?.registerModule(outModule)
    }

    fun serializeCheck(check: Check): String {
        val result = objectMapper?.writeValueAsString(check) ?: ""
        return result
    }

    fun deserializeCheck(json: String): Check {
        val result = objectMapper?.readValue(json, IPolymorphic::class.java) as Check
        return result
    }

    fun serializeTest(fake: Fake): String {
        val result = objectMapper?.writeValueAsString(fake) ?: ""
        return result
    }

    fun deserializeTest(json: String): Fake {
        val result = objectMapper?.readValue(json, IPolymorphic::class.java) as Fake
        return result
    }
}