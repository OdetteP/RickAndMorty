package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.characterDetail.CharacterDetailScreen
import com.example.myapplication.screens.characterList.CharacterListScreen
import com.example.myapplication.ui.theme.RickAndMortyComposeTheme
import com.example.myapplication.utils.Params
import com.example.myapplication.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.CHARACTER_LIST_SCREEN.route
                ) {
                    composable(Routes.CHARACTER_LIST_SCREEN.route) {
                        CharacterListScreen(navController = navController)
                    }
                    composable("${Routes.CHARACTER_DETAIL_SCREEN.route}/{${Params.DOMINANT_COLOR.param}}/{${Params.CHARACTER_ID.param}}",
                        arguments = listOf(
                            navArgument(Params.DOMINANT_COLOR.param) {
                                type = NavType.IntType
                            },
                            navArgument(Params.CHARACTER_ID.param) {
                                type = NavType.IntType
                            }
                        )) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(Params.DOMINANT_COLOR.param)
                            color?.let {
                                Color(it)
                            } ?: Color.White
                        }
                        val characterId = remember {
                            it.arguments?.getInt(Params.CHARACTER_ID.param)
                        }
                        CharacterDetailScreen(
                            dominantColor = dominantColor,
                            characterId = characterId ?: 0,
                            navController = navController
                        )
                    }

                }
            }
        }
    }


}

