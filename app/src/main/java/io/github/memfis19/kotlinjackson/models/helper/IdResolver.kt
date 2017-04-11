package io.github.memfis19.kotlinjackson.models.helper

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DatabindContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver
import com.fasterxml.jackson.databind.type.TypeFactory

/**
 * Created by Rodion on 11.04.2017.
 */
@Suppress("OverridingDeprecatedMember", "DEPRECATION")
class IdResolver : TypeIdResolver {

    private var baseType: JavaType? = null

    override fun idFromValue(value: Any?): String {
        return idFromValueAndType(value, value?.javaClass)
    }

    override fun idFromBaseType(): String {
        return idFromValueAndType(null, baseType?.rawClass)
    }

    override fun init(baseType: JavaType?) {
        this.baseType = baseType
    }

    fun typeFromId(id: String?): JavaType {
        val clazz = PolymorphicTypeRegistry.instance.get(id ?: "")
        val javaType = TypeFactory.defaultInstance().constructSpecializedType(baseType, clazz)
        return javaType
    }

    override fun typeFromId(context: DatabindContext?, id: String?): JavaType {
        return typeFromId(id)
    }

    override fun getDescForKnownTypeIds(): String {
        return ""
    }

    override fun idFromValueAndType(value: Any?, suggestedType: Class<*>?): String {
        val id = PolymorphicTypeRegistry.instance.map.filter { it.value == suggestedType }.keys
        if (!id.isEmpty()) return id.first()
        return ""
    }

    override fun getMechanism(): JsonTypeInfo.Id {
        return JsonTypeInfo.Id.CUSTOM
    }
}