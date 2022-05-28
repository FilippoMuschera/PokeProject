package com.androiders.knowthemall

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.androiders.knowthemall.api.UrlImageGetter
import com.androiders.knowthemall.database.DBPokemon
import com.androiders.knowthemall.database.RepositoryPokemon
import com.androiders.knowthemall.ui.theme.KnowThemAllTheme
import com.androiders.knowthemall.ui.theme.NetworkImageComponentPicassoV2
import com.androiders.knowthemall.ui.theme.SelectIconScreen
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class MainActivityTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //***************************TEST CON COIL/GLIDE******************

        super.onCreate(savedInstanceState)

        setContent {
            KnowThemAllTheme {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val dbPokemon = DBPokemon.getInstance(LocalContext.current)
                    val daoPokemon = dbPokemon.pokemonDAO()
                    val repositoryPokemon = RepositoryPokemon(daoPokemon)
                    val pokemonList = repositoryPokemon.listOfAllPokemon.observeAsState(listOf())

                    //ShowPokemonList(pokemonList)
                    SelectIconScreen(context = LocalContext.current, daoPokemon)

                }




            }
        }


        //***************************FINE TEST GLIDE/COIL******************



//        val picasso = Picasso.Builder(this).memoryCache(LruCache(250000000)).downloader(OkHttp3Downloader(this)).build()
//        Picasso.setSingletonInstance(picasso)
//
//        Log.d("mypkmn", "picasso creato")
//
//        super.onCreate(savedInstanceState)
//
//        val ug = UrlImageGetter()
//        for (i in 1..99) {
//            picasso.load(ug.getUrl(i)).fetch()
//        }
//
//        Log.d("mypkmn", "fetch terminato")
//
//
//
//
//        setContent {
//            KnowThemAllTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//
//                    val dbPokemon = DBPokemon.getInstance(LocalContext.current)
//                    val daoPokemon = dbPokemon.pokemonDAO()
//                    val repositoryPokemon = RepositoryPokemon(daoPokemon)
//                    val pokemonList = repositoryPokemon.listOfAllPokemon.observeAsState(listOf())
//                    val urlGetter = UrlImageGetter()
//
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp)
//                    ) {
//
//                        items(pokemonList.value) { currentPokemon ->
//
//                            Card(
//                                modifier = Modifier
//                                    .padding(8.dp)
//                                    .fillMaxWidth(), elevation = 16.dp,
//                                backgroundColor = Color(currentPokemon.color)
//                            ) {
//
//                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                                    Text(
//                                        text = currentPokemon.name + " [${currentPokemon.id}]",
//                                        color = Color.White,
//                                        textAlign = TextAlign.Center
//                                    )
//
//                                    NetworkImageComponentPicassoV2(
//                                        url = urlGetter.getUrl(
//                                            currentPokemon.id
//                                        )
//                                    )


//                                    if (currentPokemon.id < 100 && currentPokemon.id % 3 != 0) {
//
//                                        val id = resources.getIdentifier(
//                                            "com.androiders.knowthemall:drawable/p${currentPokemon.id}",
//                                            null,
//                                            null
//                                        )
//
//
//                                        Image(painter = painterResource(id = id), contentDescription = null)
//
//                                    }
//
//                                    else
//                                        NetworkImageComponentPicassoV2(url = urlGetter.getUrl(currentPokemon.id))
//
//                                }


//                                }
//
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
    }
}

