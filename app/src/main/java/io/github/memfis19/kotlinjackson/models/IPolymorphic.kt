package io.github.memfis19.kotlinjackson.models

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import io.github.memfis19.kotlinjackson.models.helper.IdResolver

/**
 * Created by Rodion on 11.04.2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.PROPERTY,
        property = "modelType")
@JsonTypeIdResolver(IdResolver::class)
interface IPolymorphic {
    val modelType: String
    companion object {
        const val ModelTypeName = "modelType"
    }
}