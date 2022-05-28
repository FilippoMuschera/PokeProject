package com.androiders.knowthemall

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.androiders.knowthemall.api.UrlImageGetter
import com.androiders.knowthemall.database.DBPokemon
import com.androiders.knowthemall.database.RepositoryPokemon
import com.androiders.knowthemall.ui.theme.KnowThemAllTheme
import com.androiders.knowthemall.ui.theme.NetworkImageComponentPicassoV2
import com.bumptech.glide.Glide

class MainActivityGlide: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowThemAllTheme {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val dbPokemon = DBPokemon.getInstance(LocalContext.current)
                    val daoPokemon = dbPokemon.pokemonDAO()
                    val repositoryPokemon = RepositoryPokemon(daoPokemon)
                    val pokemonList = repositoryPokemon.listOfAllPokemon.observeAsState(listOf())
                    val urlGetter = UrlImageGetter()

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
                                backgroundColor = Color(currentPokemon.color)
                            ) {

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                    Text(
                                        text = currentPokemon.name + " [${currentPokemon.id}]",
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                    
                                    Image(
                                        painter = rememberAsyncImagePainter(urlGetter.getUrl(currentPokemon.id)),
                                        contentDescription = "")




                                }

                            }
                        }

                    }


                }




            }
        }
    }
}