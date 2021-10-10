package com.elvisoperator.yugiohdex

import androidx.room.*
import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("data")
    var list: List<Data>
)

@Entity(tableName = "data")
data class Data(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "archetype") var archetype: String,
    @ColumnInfo(name = "atk") var atk: Int,
    @ColumnInfo(name = "attribute") var attribute: String,
    @Embedded var banlist_info: BanlistInfo,
    //@Embedded var card_images: List<CardImage>,
    //@Embedded var card_prices: List<CardPrice>,
    //@Embedded var card_sets: List<CardSet>,
    @ColumnInfo(name = "def") var def: Int,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "level") var level: Int,
    @ColumnInfo(name = "linkmarkers") var linkmarkers: String,
    @ColumnInfo(name = "linkval") var linkval: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "race") var race: String,
    @ColumnInfo(name = "scale") var scale: Int,
    @ColumnInfo(name = "type") var type: String
)

@Entity(tableName = "banlist_info")
data class BanlistInfo(
    @ColumnInfo(name = "ban_ocg") val ban_ocg: String,
    @ColumnInfo(name = "ban_tcg") val ban_tcg: String
)

@Entity(tableName = "card_image")
data class CardImage(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "image_url") val image_url: String,
    @ColumnInfo(name = "image_url_small") val image_url_small: String
)

data class DataWithCardImage(
    @Embedded val data: Data,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val card_image: List<CardImage>
)

@Entity(tableName = "card_price")
data class CardPrice(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "amazon_price") var amazon_price: String,
    @ColumnInfo(name = "cardmarket_price") var cardmarket_price: String,
    @ColumnInfo(name = "coolstuffinc_price") var coolstuffinc_price: String,
    @ColumnInfo(name = "ebay_price") var ebay_price: String,
    @ColumnInfo(name = "tcgplayer_price") var tcgplayer_price: String
)

data class DataWithCardPrice(
    @Embedded val data: Data,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val card_price: List<CardPrice>
)

@Entity(tableName = "card_set")
data class CardSet(
    @PrimaryKey var set_code: String,
    @ColumnInfo(name = "set_name") var set_name: String,
    @ColumnInfo(name = "set_price") var set_price: String,
    @ColumnInfo(name = "set_rarity") var set_rarity: String,
    @ColumnInfo(name = "set_rarity_code") var set_rarity_code: String
)

data class DataWithCardSet(
    @Embedded val data: Data,
    @Relation(
        parentColumn = "id",
        entityColumn = "set_code"
    )
    val card_set: List<CardSet>
)







