package io.github.memfis19.kotlinjackson.models.helper

import io.github.memfis19.kotlinjackson.models.IPolymorphic
import java.util.*

/**
 * Created by Rodion on 11.04.2017.
 */
class PolymorphicTypeRegistry private constructor() {

    var map: HashMap<String, Class<out IPolymorphic>> = HashMap()

    private object Holder { val instance = PolymorphicTypeRegistry() }

    companion object {
        val instance: PolymorphicTypeRegistry by lazy { Holder.instance }
    }

    fun add(modelType: String, type: Class<out IPolymorphic>) {
        map.put(modelType, type)
    }

    fun get(modelType: String): Class<out IPolymorphic>? {
        val result = map[modelType] ?: return null
        return result
    }
}