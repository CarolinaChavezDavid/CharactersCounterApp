package com.carolina.characterscounterapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.carolina.characterscounterapp.ui.theme.titleLargeStyle

@Composable
fun CounterComponent(
    modifier: Modifier,
    title: String,
    characterList: List<String>,
) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = title, style = titleLargeStyle)
        Card(
            modifier =
                Modifier.fillMaxWidth()
                    .border(2.dp, Chartreuse),
            shape = RoundedCornerShape(16.dp),
        ) {
            LazyColumn(modifier = Modifier.padding(24.dp)) {
                items(characterList) {
                    Text(text = it)
                }
            }
        }
    }
}
