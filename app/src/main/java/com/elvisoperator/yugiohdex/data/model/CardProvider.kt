package com.elvisoperator.yugiohdex.data.model

import com.elvisoperator.yugiohdex.data.CardModel
import com.elvisoperator.yugiohdex.data.Data

class CardProvider {
    companion object{
        var basicCards = emptyList<BasicCard>()
        var allCards = emptyList<Data>()
    }
}