package com.example.myapplication.screens.characterList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.data.models.CharacterListEntry
import com.example.myapplication.utils.Routes

@Composable
fun CharactersListScreen(
    navController: NavController,
    viewModel: CharactersListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            AsyncImage(
                model = R.drawable.rick_morty,
                contentDescription = null,
            )
            SearchBar(
                hint = "Search a character",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchCharacterList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CharacterList(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    val isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun CharacterList(
    navController: NavController,
    viewModel: CharactersListViewModel = hiltViewModel()
) {
    val characterList by remember { viewModel.characterList }
    val isEndOfList by remember { viewModel.isEndOfList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (characterList.size % 2 == 0) {
            characterList.size / 2
        } else {
            characterList.size / 2 + 1
        }

        items(itemCount) {
            if (it >= itemCount - 1 && !isEndOfList && !isLoading && !isSearching) {
                viewModel.loadPaginatedCharacters()
            }
            CharacterRow(rowIndex = it, characters = characterList, navController = navController)
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()) {
            RetryLayout(error = loadError) {
                viewModel.loadPaginatedCharacters()
            }
        }
    }
}


@Composable
fun CharacterEntry(
    character: CharacterListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "${Routes.CHARACTER_DETAIL_SCREEN.route}/${character.characterId}"
                )
            }

    ) {
        Column {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = character.characterName,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CharacterRow(
    rowIndex: Int,
    characters: List<CharacterListEntry>,
    navController: NavController
) {
    Column {
        Row {
            CharacterEntry(
                character = characters[rowIndex * 2],
                navController = navController,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (characters.size >= rowIndex * 2 + 2) {
                CharacterEntry(
                    character = characters[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()

                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RetryLayout(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(
            text = error,
            color = Color.Red,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }

    }
}