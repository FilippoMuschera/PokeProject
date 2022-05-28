package com.androiders.knowthemall.ui.theme

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.androiders.knowthemall.R
import com.androiders.knowthemall.model.MyColor
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NetworkImageComponentPicasso(
    url: String,
    modifier: Modifier = Modifier,
    myColor: MutableState<MyColor>
) {
    val context = LocalContext.current
    val sizeModifier = modifier
        .size(128.dp, 128.dp)
        .sizeIn(maxHeight = 40.dp)

    val error = BitmapFactory.decodeResource(context.resources, R.drawable.img)
    val waitplease = BitmapFactory.decodeResource(context.resources, R.drawable.img)

    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    DisposableEffect(url) {
        val picasso =
            Picasso.get()//Picasso.Builder(context).memoryCache(LruCache(48000000)).build() e poi Picasso.setSingleton(picasso) ma va fatto nell' onCreate()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                image = waitplease.asImageBitmap()
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                image = error.asImageBitmap()
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageBitmap()
            }

        }
        picasso
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .into(target)
        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable





    if (theImage != null) {
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(bitmap = theImage, contentDescription = null)
        }
    } /* else if (theDrawable != null) {
            Canvas(modifier = sizeModifier) {
                drawIntoCanvas { canvas ->
                    theDrawable.draw(canvas.nativeCanvas)
                }
            }
        }*/

    CoroutineScope(Dispatchers.Default).launch {
        if (theImage != null) {
            //val avgColor= avg.getAvgColor(theImage.asAndroidBitmap())
            var vibrant: Int = 0

            Palette.Builder(theImage.asAndroidBitmap()).generate {
                it?.let { palette ->
                    vibrant = palette.getLightMutedColor(0x000000) // <=== color you want
                }


                myColor.value = MyColor(Color(vibrant))

            }
        }
    }
}


@Composable
fun NetworkImageComponentPicassoV2(
    url: String,
    modifier: Modifier = Modifier,
) {



    val context = LocalContext.current
    val sizeModifier = modifier
        .size(128.dp, 128.dp)
        .sizeIn(maxHeight = 40.dp)

    val error = BitmapFactory.decodeResource(context.resources, R.drawable.img)
    val waitplease = BitmapFactory.decodeResource(context.resources, R.drawable.img)

    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    DisposableEffect(url) {
        val picasso =
            Picasso.get()//Picasso.Builder(context).memoryCache(LruCache(48000000)).build() e poi Picasso.setSingleton(picasso) ma va fatto nell' onCreate()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                image = waitplease.asImageBitmap()
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                image = error.asImageBitmap()
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageBitmap()
            }

        }
        picasso
            .load(url)
            .priority(Picasso.Priority.HIGH)
            .resize(800, 800)
            .error(R.drawable.ic_launcher_foreground)
            .into(target)
        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable





    if (theImage != null) {
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(bitmap = theImage, contentDescription = null)
        }
    } /* else if (theDrawable != null) {
            Canvas(modifier = sizeModifier) {
                drawIntoCanvas { canvas ->
                    theDrawable.draw(canvas.nativeCanvas)
                }
            }
        }*/

}


