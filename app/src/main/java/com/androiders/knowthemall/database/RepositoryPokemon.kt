package com.androiders.knowthemall.database

import androidx.lifecycle.LiveData
import com.androiders.knowthemall.model.PokemonBase

class RepositoryPokemon(private val daoPokemon: PokemonDAO) {

    var listOfAllPokemon: LiveData<List<PokemonBase>> = daoPokemon.testList()

    var listOfFavourites: LiveData<List<PokemonBase>> = daoPokemon.getAllFavourites()


}