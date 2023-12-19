package dev.jay.wouldurather.models

data class DilemmaModel(
    val v: Int?,
    val idDatabases: String?,
    val active: Boolean?,
    val id: Int?,
    val option1: String,
    val option1Votes: Int,
    val option1PT: String?,
    val option2: String,
    val option2Votes: Int,
    val option2PT: String?,
    val recommended: Boolean?
)