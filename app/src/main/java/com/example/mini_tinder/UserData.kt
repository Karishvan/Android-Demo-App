package com.example.mini_tinder

data class UserResponse (
    val results: List<UserData>
)

data class UserData(
    val name: Name,
    val gender: String,
    val location: Location,
    val email: String,
    val picture: Picture
)

data class Name(
    val first: String,
    val last: String
)

data class Location(
    val city: String,
    val state: String,
    val country: String
)

data class Picture (
    val large: String
)
