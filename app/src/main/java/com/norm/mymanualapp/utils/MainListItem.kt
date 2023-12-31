package com.norm.mymanualapp.utils

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.norm.mymanualapp.MainViewModel
import com.norm.mymanualapp.ui.theme.BGLightGrayishCyan
import com.norm.mymanualapp.ui.theme.Mint

@Composable
fun MainListItem(
    mainViewModel: MainViewModel = hiltViewModel(), item: ListItem, onClick: (ListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(5.dp)
            .clickable {
                onClick(item)
            }, shape = RoundedCornerShape(10.dp), border = BorderStroke(2.dp, Mint)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, text, favoriteButton) = createRefs()

            AssetImage(imageName = item.imgName,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    })
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Mint)
                    .padding(10.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom, 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                text = item.title
            )
            IconButton(onClick = {
                mainViewModel.insertItem(
                    item.copy(isFav = !item.isFav)
                )
            }, modifier = Modifier.constrainAs(favoriteButton) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }) {
                Icon(
                    imageVector = if (item.isFav)
                        Icons.Default.Favorite
                    else
                        Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (item.isFav) Color.Red else Color.Gray,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BGLightGrayishCyan)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun AssetImage(imageName: String, contentDescription: String, modifier: Modifier) {
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}