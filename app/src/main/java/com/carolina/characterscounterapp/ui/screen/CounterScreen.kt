package com.carolina.characterscounterapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carolina.characterscounterapp.R
import com.carolina.characterscounterapp.ui.viewModel.CharactersViewModel
import com.carolina.characterscounterapp.ui.viewModel.UiState
import com.carolina.characterscounterapp.ui.component.CounterComponent
import com.carolina.characterscounterapp.ui.theme.titleLargeStyle
import com.carolina.characterscounterapp.utils.getCounterList

@Composable
fun CounterScreenContent(
    every10thCharacter: List<String>,
    wordCounter: Map<String, Int>,
    onButtonClicked: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            CounterComponent(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                title = stringResource(id = R.string.ten_character),
                characterList = every10thCharacter,
            )
            CounterComponent(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                title = stringResource(id = R.string.word_counter),
                characterList = getCounterList(wordCounter),
            )
            Button(onClick = { onButtonClicked() }, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.button_text), style = titleLargeStyle)
            }
        }
    }
}

@Composable
fun CounterScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by charactersViewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (uiState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Success ->
                CounterScreenContent(
                    every10thCharacter = charactersViewModel.every10thCharacter.collectAsState().value,
                    wordCounter = charactersViewModel.wordCounter.collectAsState().value,
                    onButtonClicked = { charactersViewModel.getData() },
                )

        }
    }
}
