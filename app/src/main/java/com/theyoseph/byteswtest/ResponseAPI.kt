package com.theyoseph.byteswtest

typealias ResponseAPI = ArrayList<ResponseAPIElement>

data class ResponseAPIElement (
    val id: String,
    val type: String,
    val name: String,
    val ppu: Double,
    val batters: Batters,
    val topping: List<Topping>
)

data class Batters (
    val batter: List<Topping>
)

data class Topping (
    val id: String,
    val type: String
)