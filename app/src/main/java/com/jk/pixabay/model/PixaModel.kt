package com.jk.pixabay.model

data class PixaModel(
    var hits: ArrayList<ImageModel>)

data class ImageModel(
    var largeImageURL: String
)
