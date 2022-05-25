package com.androiders.knowthemall

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androiders.knowthemall.database.DBPokemon
import com.androiders.knowthemall.ui.theme.KnowThemAllTheme

class MainActivityTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            KnowThemAllTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val db = DBPokemon.getInstance(this)
                    val dao = db.pokemonDAO()

                    dao.removeFavourite(1)
                    var myPokemon = dao.getPokemonBase("Bulbasaur")
                    Log.d("MYPKTEST", myPokemon.name + myPokemon.id + myPokemon.color + "," + myPokemon.favourite)
                    dao.setFavourite(1)
                    myPokemon = dao.getPokemonBase("Bulbasaur")
                    Log.d("MYPKTEST", myPokemon.name + myPokemon.id + myPokemon.color + "," + myPokemon.favourite)
                    dao.removeFavourite(1)


                }
            }
        }
    }
}

