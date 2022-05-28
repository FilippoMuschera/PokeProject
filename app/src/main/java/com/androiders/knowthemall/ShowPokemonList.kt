package com.androiders.knowthemall

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.androiders.knowthemall.api.UrlImageGetter
import com.androiders.knowthemall.model.PokemonBase


@Composable
fun ShowPokemonList(pokemonList: State<List<PokemonBase>>){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        items(pokemonList.value) { currentPokemon ->

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), elevation = 16.dp,
                backgroundColor = Color(currentPokemon.color),
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = currentPokemon.name + " [${currentPokemon.id}]",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    val urlGetter = UrlImageGetter()

                    val painter = rememberAsyncImagePainter(model = ImageRequest.Builder(
                        LocalContext.current)
                        .size(Size.ORIGINAL)
                        .data(urlGetter.getUrl(currentPokemon.id))
                        .build())

                    if (painter.state !is AsyncImagePainter.State.Success)
                        CircularProgressIndicator(modifier = Modifier.fillMaxHeight()
                            .padding(8.dp),
                            color = Color.White)

                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier.size(128.dp)
                    )




                }

            }
        }

    }
}
