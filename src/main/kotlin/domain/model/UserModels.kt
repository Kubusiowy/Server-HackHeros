package com.hackheros.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind

@Serializable
data class UserResponse(
    val id : Int,
    val username: String,
    val email : String,
    val password : String,
    val streak : Int = 0,
    val experience:Int = 0,
    val prefered_category_Id:Int?
)

@Serializable
data class UserRequest(
    val username: String,
    val email : String,
    val password : String,
    val streak : Int = 0,
    val experience:Int = 0,
    val prefered_category_Id:Int?
)