package com.androiders.knowthemall

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.androiders.knowthemall.api.PokeAPI
import com.androiders.knowthemall.model.MyColor
import com.androiders.knowthemall.model.Pokemon
import com.androiders.knowthemall.ui.theme.NetworkImageComponentPicasso
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var listaPokemon: List<String> =
                listOf("bulbasaur", "dratini", "dialga", "mew", "mewtwo")
            NuovaInterfaccia(listaPokemon)
        }
    }
}

/*@Composable
fun nuovaInterfaccia(list: List<String>){
    Surface() {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            val (label, column, text, secondColumn, card, image) = createRefs()
            TopAppBar(modifier = Modifier
                .constrainAs(label) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(color = Color.Red)
                .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Know'Em All",
                        textAlign = TextAlign.Center
                    )
                }
            }

            LazyColumn(modifier = Modifier
                .background(color = Color.Black)
                .constrainAs(column) {
                    top.linkTo(label.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ){
                itemsIndexed(items = list) {index,detail ->
                    
                    val p = PokeApi(LocalContext.current)
                    val poke = Pokemon()
                    val myPokemon: MutableState<Pokemon> = rememberSaveable {
                        mutableStateOf(poke)
                    }
                    p.getPokemon(list[index], {
                        val sType = object : TypeToken<Pokemon>() {}.type

                        val gson = Gson()
                        val res = gson.fromJson<Pokemon>(it, sType)
                        myPokemon.value = res
                    }, {
                        Log.d("POKE", "Error retrieving Pokemon from API")
                    })

                    if (myPokemon.value.name != "") {
                        val myFontFamily = FontFamily(Font(R.font.jbmono_medium))

                        val myColor = rememberSaveable {
                            mutableStateOf(MyColor())
                        }

                        Card(
                            elevation = 4.dp,
                            backgroundColor = myColor.value.myColor,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .constrainAs(card) {
                                    top.linkTo(column.top)
                                    start.linkTo(column.start)
                                    end.linkTo(column.end)
                                }
                        ) {

                            Column(
                                Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .constrainAs(secondColumn) {
                                        top.linkTo(card.top)
                                        start.linkTo(column.start)
                                        end.linkTo(column.end)
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = myPokemon.value.name[0].uppercase() + myPokemon.value.name.substring(1),
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    fontFamily = myFontFamily
                                )

                                NetworkImageComponentPicasso(url = p.getImage(myPokemon.value.id),
                                    myColor = myColor)

                            }

                        }
                    }
                }

            }
        }
    }
}*/

@Composable
fun NuovaInterfaccia(list: List<String>) {
    Surface() {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            val (label, column) = createRefs()
            TopAppBar(modifier = Modifier
                .constrainAs(label) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(color = Color.Red)
                .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Know'Em All",
                        textAlign = TextAlign.Center
                    )
                }
            }
            val state = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .constrainAs(column) {
                        top.linkTo(label.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                state = state,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
            ) {
                item { ShowPokemons(list) }
            }
        }
    }
}

@Composable
fun ShowPokemons(list: List<String>) {
    var len = list.size
    var i = 0
    while (i <= len - 1) {
        Row() {
            var modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(0.5f)
            ShowPokemon(list[i], modifier)
            i++

            if (i != len) {
                var mod = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                ShowPokemon(list[i], mod)
                i++
            }
        }
    }
}


@Composable
fun ShowPokemon(name: String, mod: Modifier) {
    val p = PokeAPI(LocalContext.current)
    val poke = Pokemon()
    val myPokemon: MutableState<Pokemon> = rememberSaveable {
        mutableStateOf(poke)
    }
    p.getPokemon(name, {
        val sType = object : TypeToken<Pokemon>() {}.type

        val gson = Gson()
        val res = gson.fromJson<Pokemon>(it, sType)
        myPokemon.value = res
    }, {
        Log.d("POKE", "Error retrieving Pokemon from API")
    })

    if (myPokemon.value.name != "") {


        val myFontFamily = FontFamily(Font(R.font.jbmono_medium))

        val myColor = rememberSaveable {
            mutableStateOf(MyColor())
        }

        Card(
            elevation = 4.dp,
            backgroundColor = myColor.value.myColor,
            modifier = mod
        ) {

            Column(
                Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //In teoria avendo i colori nel db ora questa parte può/deve essere modificata

                NetworkImageComponentPicasso(
                    url = p.getImage(myPokemon.value.id),
                    myColor = myColor
                )

                Text(
                    text = myPokemon.value.name[0].uppercase() + myPokemon.value.name.substring(1),
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White, RectangleShape),
                    fontFamily = myFontFamily
                )

            }

        }


    }


}



