package com.androiders.knowthemall.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class PokeAPI(private val context: Context) {

    fun getPokemonByID(
        pokemonID: Int,
        onSuccess: (String) -> Unit,
        onError: (VolleyError) -> Unit
    ) {
        val url = "https://pokeapi.co/api/v2/pokemon/$pokemonID"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                onSuccess(it)
            },
            {
                onError(it)
            })
        queue.add(stringRequest)
    }

    fun getPokemon(name: String, onSuccess: (String) -> Unit, onError: (VolleyError) -> Unit) {
        val url = "https://pokeapi.co/api/v2/pokemon/$name"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                onSuccess(it)
            },
            {
                onError(it)
            })
        queue.add(stringRequest)
    }

    fun getImage(id: Int): String { //TODO QUESTA VA TOLTA E SOSTITUITA CON getUrl(id: Int)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }


}


