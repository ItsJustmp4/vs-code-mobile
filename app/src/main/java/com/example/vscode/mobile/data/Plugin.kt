package com.example.vscode.mobile.data

data class Plugin(
    val id: String,
    val name: String,
    val publisher: String,
    val description: String,
    val version: String,
    val category: String,
    val downloads: String,
    val rating: Float,
    val tags: List<String>,
    val installed: Boolean = false
)