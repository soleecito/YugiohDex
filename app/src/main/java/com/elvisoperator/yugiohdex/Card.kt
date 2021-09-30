package com.elvisoperator.yugiohdex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
    @Ignore var banlist_info: BanlistInfo?,
    @Ignore var card_images: List<CardImage>?,
    @Ignore var card_prices: List<CardPrice>?,
    @Ignore var card_sets: List<CardSet>?,
    @ColumnInfo(name = "def") var def: Int,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "level") var level: Int,
    @Ignore var linkmarkers: List<String>?,
    @ColumnInfo(name = "linkval") var linkval: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "race") var race: String,
    @ColumnInfo(name = "scale") var scale: Int,
    @ColumnInfo(name = "type") var type: String
)

@Entity(tableName = "banlist")
data class BanlistInfo(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "ban_ocg") var ban_ocg: String,
    @ColumnInfo(name = "ban_tcg") var ban_tcg: String
)

@Entity(tableName = "cardimage")
data class CardImage(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "image_url")var image_url: String,
    @ColumnInfo(name = "image_small")var image_url_small: String
)

@Entity(tableName = "cardprice")
data class CardPrice(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "amzprice")var amazon_price: String,
    @ColumnInfo(name ="cardmktprice")var cardmarket_price: String,
    @ColumnInfo(name = "coolstufprice")var coolstuffinc_price: String,
    @ColumnInfo(name ="ebayprice")var ebay_price: String,
    @ColumnInfo(name ="tcgplayerprice")var tcgplayer_price: String
)

@Entity(tableName = "cardSet")
data class CardSet(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "set_code")var set_code: String,
    @ColumnInfo(name = "set_name")var set_name: String,
    @ColumnInfo(name = "set_price")var set_price: String,
    @ColumnInfo(name = "set_rarity")var set_rarity: String,
    @ColumnInfo(name = "set_rarity_code")var set_rarity_code: String
)






