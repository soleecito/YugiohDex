package com.elvisoperator.yugiohdex.data.model

data class BasicCardModel(
    var list: List<BasicCard>
)

data class BasicCard(
    var name: String,
    var type: String,
    var level: String,
    var image: BasicCardImage
)

data class BasicCardImage(
    var id: Int,
    var image_url: String,
    var image_url_small: String
)
