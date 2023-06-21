package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.characterDetail.CharacterDetailScreen
import com.example.myapplication.screens.characterList.CharactersListScreen
import com.example.myapplication.ui.theme.RickAndMortyComposeTheme
import com.example.myapplication.utils.Params
import com.example.myapplication.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                        CharactersListScreen(navController = navController)
                    }
                    composable("${Routes.CHARACTER_DETAIL_SCREEN.route}/{${Params.CHARACTER_ID.param}}",
                        arguments = listOf(
                            navArgument(Params.CHARACTER_ID.param) {
                                type = NavType.IntType
                            }
                        )) {
                        val characterId = remember {
                            it.arguments?.getInt(Params.CHARACTER_ID.param)
                        }
                        CharacterDetailScreen(
                            characterId = characterId ?: 0,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}





