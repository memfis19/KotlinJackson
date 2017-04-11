package io.github.memfis19.kotlinjackson.models.helper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import io.github.memfis19.kotlinjackson.models.IPolymorphic

/**
 * Created by Rodion on 11.04.2017.
 */
class PolymorphicJsonDeserializer(moduleRegistrationHandler: ModuleRegistrationHandler) : com.fasterxml.jackson.databind.JsonDeserializer<IPolymorphic>() {

    interface ModuleRegistrationHandler {
        fun handleRegistration(module: SimpleModule)
    }

    val objectMapperInner: ObjectMapper

    init {
        objectMapperInner = ObjectMapper()
        val module: SimpleModule = SimpleModule()
        moduleRegistrationHandler.handleRegistration(module)
        objectMapperInner.registerModule(module)
    }

    override fun deserialize(json: JsonParser?, context: DeserializationContext?): IPolymorphic? {
        val node: JsonNode? = json?.codec?.readTree(json)
        val modelType: String? = node?.get(IPolymorphic.ModelTypeName)?.asText()
        if (modelType != null) {
            return objectMapperInner.readValue(node.toString(), PolymorphicTypeRegistry.instance.get(modelType))
        }

        return null
    }
}