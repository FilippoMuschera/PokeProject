package com.androiders.knowthemall.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
class PokemonBase {
    @PrimaryKey
    var id: Int = 0

    var name: String = ""
    var color: Int = 0
    var favourite = 0

}