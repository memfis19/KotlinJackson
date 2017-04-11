package io.github.memfis19.kotlinjackson.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Rodion on 11.04.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class Check(var name: String? = "",
                 @get:JsonProperty("isJacksonWork") var isJacksonWork: Boolean? = false,
                 var test: Boolean? = false) : IPolymorphic {

    @JsonCreator
    constructor() : this(null)

    companion object {
        const val ModelTypeName = "Check"
    }

    override val modelType: String
        get() = ModelTypeName

    override fun equals(other: Any?): Boolean {
        if (other !is Check) return false
        val obj = other as Check

        return (this.name.equals(obj.name)) && this.isJacksonWork == obj.isJacksonWork && this.test == obj.test
    }
}