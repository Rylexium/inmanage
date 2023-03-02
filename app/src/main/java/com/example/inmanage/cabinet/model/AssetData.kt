package com.example.inmanage.cabinet.model

data class AssetData(
    val descriptionField: String,
    val descriptionImage: Int,
    val eventAfterClick: () -> Unit
)
