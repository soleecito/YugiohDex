package com.elvisoperator.yugiohdex.data.model

//data class QuoteModel (val quote : String , val author: String)

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardModel( @SerializedName("data")
                 var list: MutableList<Data>  ) : Parcelable

@Parcelize
data class Data(
    var archetype: String,
    var atk: Int,
    var attribute: String,
    var banlist_info: BanlistInfo,
    var card_images: List<CardImage>,
    var card_prices: List<CardPrice>,
    var card_sets: List<CardSet>,
    var def: Int,
    var desc: String,
    var id: Int,
    var level: Int,
    var linkmarkers: List<String>,
    var linkval: Int,
    var name: String,
    var race: String,
    var scale: Int,
    var type: String
): Parcelable

@Parcelize
data class BanlistInfo(
    var ban_ocg: String,
    var ban_tcg: String
): Parcelable

@Parcelize
data class CardImage(
    var id: Int,
    var image_url: String,
    var image_url_small: String
): Parcelable



@Parcelize
data class CardPrice(
    var amazon_price: String,
    var cardmarket_price: String,
    var coolstuffinc_price: String,
    var ebay_price: String,
    var tcgplayer_price: String
): Parcelable

@Parcelize
data class CardSet(
    var set_code: String,
    var set_name: String,
    var set_price: String,
    var set_rarity: String,
    var set_rarity_code: String
): Parcelable
