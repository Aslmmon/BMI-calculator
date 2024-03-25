package com.ontop.moviesapp.ui.shared.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ontop.moviesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DebouncedBasicInput(
    text: String,
    onTextChanged: (String) -> Unit,
    debouncePeriod: Long = 1000 // Adjust debounce period as needed
) {
    var job by remember { mutableStateOf<Job?>(null) }
    var currentText by remember { mutableStateOf(text) }

    DisposableEffect(Unit) {
        onDispose {
            job?.cancel()
        }
    }

    BasicTextField(
        value = currentText,
        singleLine = true,
        onValueChange = {
            currentText = it
            job?.cancel()
            job = CoroutineScope(Dispatchers.Main).launch {
                delay(debouncePeriod)
                onTextChanged(currentText)
            }
        }, decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color(0XFF4c8acc),
                        shape = RoundedCornerShape(10.dp)
                    )

            ) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Edit, contentDescription = "")
                    if (currentText.isEmpty()) {
                        Text(text = stringResource(id = R.string.enter_text))
                    }
                    innerTextField.invoke()
                }
            }
        }
    )
}
