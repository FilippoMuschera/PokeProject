package com.androiders.knowthemall.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiders.knowthemall.model.PokemonBase


@Database(
    entities = [PokemonBase::class],
    version = 1
)

abstract class DBPokemon:RoomDatabase() {
companion object {
    private var db: DBPokemon? = null //Singleton
    fun getInstance(context: Context): DBPokemon {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                DBPokemon::class.java,
                "pokemon.db"
            )
                .createFromAsset("pokemonv2.db")
                .allowMainThreadQueries() //TODO SOLO PER TEST, VA TOLTO
                .build()
        }

            return db as DBPokemon
        }
    }

    abstract fun pokemonDAO():PokemonDAO

}

