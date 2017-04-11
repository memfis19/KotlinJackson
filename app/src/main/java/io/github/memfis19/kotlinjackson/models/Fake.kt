package io.github.memfis19.kotlinjackson.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Rodion on 11.04.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class Fake(var name: String? = "",
                @get:JsonProperty("isJacksonWork") var isJacksonWork: Boolean? = false,
                var test: Boolean? = false) : IPolymorphic {

    @JsonCreator
    constructor() : this(null)

    companion object {
        const val ModelTypeName = "Fake"
    }

    override val modelType: String
        get() = ModelTypeName

    override fun equals(other: Any?): Boolean {
        if (other !is Fake) return false
        val obj = other as Fake

        return (this.name.equals(obj.name)) && this.isJacksonWork == obj.isJacksonWork && this.test == obj.test
    }
}