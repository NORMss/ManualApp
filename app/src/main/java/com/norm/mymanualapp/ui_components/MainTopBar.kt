package com.norm.mymanualapp.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.norm.mymanualapp.MainViewModel
import com.norm.mymanualapp.utils.DrawerEvents
import com.norm.mymanualapp.utils.ListItem
import com.norm.mymanualapp.utils.MainListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainTopBar(
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainList = mainViewModel.mainList
    val topBarTitle = remember {
        mutableStateOf("Jetpack Compose")
    }
    LaunchedEffect(Unit) {
        mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu() { event ->
                    when (event) {
                        is DrawerEvents.OnItemClick -> {
                            topBarTitle.value = event.title
                            mainViewModel.getAllItemsByCategory(event.title)
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            mainViewModel.getFavorites()
                            topBarTitle.value = "Favorites"
                        })
                        {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite"
                            )
                        }
                    },
                    title = {
                        Text(text = topBarTitle.value)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            3.dp
                        )
                    )
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding())
                ) {
                    items(mainList.value) { item ->
                        MainListItem(item = item) { listItem ->
                            onClick(listItem)
                        }
                    }
                }
            }
        )
    }
}