package com.hackheros.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    val id : Int,
    val username: String,
    val email : String,
    val streak : Int = 0,
    val experience:Int = 0,
    val preferredCategoryId:Int?
)

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

data class UserRecord(
    val id: Int,
    val username: String,
    val email: String,
    val hashPass: String,
    val streak: Int,
    val experience: Int,
    val preferredCategoryId: Int?
)

fun UserRecord.toUserResponse() = UserResponse(
    id = id,
    username = username,
    email = email,
    streak = streak,
    experience = experience,
    preferredCategoryId = preferredCategoryId
)

@Serializable
data class UpdatePreferencesRequest(
    val preferredCategoryId: Int?
)

@Serializable
data class AuthResponse(
    val user: UserResponse,
    val token: String
)