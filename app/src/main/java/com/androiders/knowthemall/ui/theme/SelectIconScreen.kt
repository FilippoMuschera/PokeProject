package com.androiders.knowthemall.ui.theme

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.androiders.knowthemall.R
import com.androiders.knowthemall.database.PokemonDAO

@Composable
fun SelectIconScreen(context: Context, daoPokemon: PokemonDAO) {

    val myFont = FontFamily(Font(R.font.jbmono_medium))

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        val bulbasaur = daoPokemon.getPokemonBase("bulbasaur")
        val charmander = daoPokemon.getPokemonBase("charmander")
        val squirtle = daoPokemon.getPokemonBase("squirtle")

        val (label, pic1, pic2, pic3, btn) = createRefs()

        Text(text = context.getString(R.string.selectIcon),
            modifier = Modifier.constrainAs(label) {
            top.linkTo(parent.top, margin = 8.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)

        }.padding(8.dp), textAlign = TextAlign.Center, fontSize = 20.sp, fontFamily = myFont)

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(pic1) {
            top.linkTo(label.bottom, margin = 32.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)

        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(bulbasaur.color))
        ) {
            Column {
                val id = context.resources.getIdentifier(
                    "com.androiders.knowthemall:drawable/p${bulbasaur.id}",
                    null,
                    null
                )

                Image(painter = painterResource(id = id), contentDescription = null)

                Text(
                    text = "Bulbasaur",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally), fontFamily = myFont
                )
            }

        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(pic2) {
            top.linkTo(pic1.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(charmander.color))
        ) {
            Column {
                val id = context.resources.getIdentifier(
                    "com.androiders.knowthemall:drawable/p${charmander.id}",
                    null,
                    null
                )

                Image(painter = painterResource(id = id), contentDescription = null)

                Text(
                    text = "Charmander",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally), fontFamily = myFont
                )
            }

        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(pic3) {
            top.linkTo(pic2.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(squirtle.color))
        ) {
            Column {
                val id = context.resources.getIdentifier(
                    "com.androiders.knowthemall:drawable/p${squirtle.id}",
                    null,
                    null
                )

                Image(painter = painterResource(id = id), contentDescription = null)

                Text(
                    text = "Squirtle",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally), fontFamily = myFont
                )
            }

        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(btn) {
            top.linkTo(pic3.bottom, margin = 12.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
        }) {

            Text(text = context.getString(R.string.reset), fontFamily = myFont)

        }


    }
}