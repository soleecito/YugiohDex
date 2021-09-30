package com.elvisoperator.yugiohdex

import androidx.room.*
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
    @Embedded var banlist_info: BanlistInfo,
    @Embedded var card_images: CardImage,
    @Embedded var card_prices: CardPrice,
    @Embedded var card_sets: CardSet,
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

data class BanlistInfo(
    val ban_ocg: String,
    val ban_tcg: String
)

data class CardImage(
    val image_url: String,
    val image_url_small: String
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






