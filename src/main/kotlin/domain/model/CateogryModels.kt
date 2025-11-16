package com.hackheros.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id:Int,
    val name:String,
    val description:String?,
)

@Serializable
data class CategoryRequest(
    val name:String,
    val description:String?,
)