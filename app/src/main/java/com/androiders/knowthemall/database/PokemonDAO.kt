package com.androiders.knowthemall.database

import androidx.room.Dao
import androidx.room.Query
import com.androiders.knowthemall.model.PokemonBase

@Dao
interface PokemonDAO {


    @Query("SELECT * FROM Pokemon")
    fun getAllPokemonBase(): List<PokemonBase>

    /*
    Per fare una ricerca di un pokemon con solo parte del nome, passare alla funzione la stringa
    formattata come "nomeParzialeDelPokemon%" -> es.:"bulbas%"
     */

    @Query("SELECT * FROM Pokemon WHERE name LIKE :pokemonName")
    fun getPokemonBase(pokemonName: String): PokemonBase //Il nome NON è case sensitive: Bulbasaur = bulbasaur



    @Query("UPDATE Pokemon SET favourite = 1 WHERE id = :pokemonID")
    fun setFavourite(pokemonID: Int)

    @Query("UPDATE Pokemon SET favourite = 0 WHERE id = :pokemonID")
    fun removeFavourite(pokemonID: Int)

    @Query("SELECT * FROM Pokemon WHERE favourite = 1")
    fun getAllFavourites(): List<PokemonBase>




}