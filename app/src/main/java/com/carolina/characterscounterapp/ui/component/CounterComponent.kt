package com.carolina.characterscounterapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carolina.characterscounterapp.ui.theme.Chartreuse

@Composable
fun CounterComponent(
    modifier: Modifier,
    title: String,
    characterList: List<String>,
) {
    Column(modifier = modifier.padding(24.dp)) {
        Text(text = title)
        Card(
            modifier =
                Modifier.fillMaxWidth()
                    .border(2.dp, Chartreuse),
            shape = RoundedCornerShape(8.dp),
        ) {
            LazyColumn(modifier = Modifier.padding(24.dp)) {
                items(characterList) {
                    Text(text = it)
                }
            }
        }
    }
}
