package com.elvisoperator.yugiohdex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("data")
    var list: List<Data>
)

@Entity(tableName = "data")
data class Data(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "archetype") var archetype: String,
    @ColumnInfo(name = "atk") var atk: Int,
    @ColumnInfo(name = "attribute") var attribute: String,
    @ColumnInfo(name = "banlist_info") var banlist_info: BanlistInfo,
    @ColumnInfo(name = "card_images") var card_images: List<CardImage>,
    @ColumnInfo(name = "card_prices") var card_prices: List<CardPrice>,
    @ColumnInfo(name = "card_sets") var card_sets: List<CardSet>,
    @ColumnInfo(name = "def") var def: Int,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "level") var level: Int,
    @ColumnInfo(name = "linkmarkers") var linkmarkers: List<String>,
    @ColumnInfo(name = "linkval") var linkval: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "race") var race: String,
    @ColumnInfo(name = "scale") var scale: Int,
    @ColumnInfo(name = "type") var type: String
)

data class BanlistInfo(
    var ban_ocg: String,
    var ban_tcg: String
)

data class CardImage(
    var id: Int,
    var image_url: String,
    var image_url_small: String
)

data class CardPrice(
    var amazon_price: String,
    var cardmarket_price: String,
    var coolstuffinc_price: String,
    var ebay_price: String,
    var tcgplayer_price: String
)

data class CardSet(
    var set_code: String,
    var set_name: String,
    var set_price: String,
    var set_rarity: String,
    var set_rarity_code: String
)






