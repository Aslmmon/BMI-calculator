package com.ontop.inputapp.ui

import android.content.ActivityNotFoundException
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.BMIIndexUrl
import com.ontop.genders
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.AgeContent
import com.ontop.inputapp.shared_ui.BMIButton
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.HeightContent
import com.ontop.inputapp.shared_ui.TitleScreen
import com.ontop.inputapp.shared_ui.WeightContent
import com.ontop.inputapp.shared_ui._gap


@Composable
fun BmiInputScreen(
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TitleScreen(stringResource(id = R.string.bmi_calculator_title))
        _gap(height = 10)
        ContentWithTitle(title = stringResource(R.string.gender_title)) {
            GenderContent()
        }
        ContentWithTitle(title = stringResource(R.string.age_title)) {
            AgeContent()
        }

        ContentWithTitle(title = stringResource(R.string.height)) {
            HeightContent()
        }

        ContentWithTitle(title = stringResource(R.string.weight)) {
            WeightContent()
        }


    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.learn_text), fontSize = 10.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    try {
                        uriHandler.openUri(BMIIndexUrl)
                    } catch (e: Exception) {
                        Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT).show()
                    }
                },
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            _gap()
            BMIButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                stringResource(R.string.calculate_bmi),
                onClick = { })
        }
    }

}

@Composable
private fun GenderContent() {
    var selectedGender by remember { mutableStateOf<Int>(-1) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround // Distribute items evenly
    ) {
        itemsIndexed(genders) { index, item ->
            GenderView(
                modifier = Modifier, gender = item, onGenderClicked = {
                    selectedGender = index
                }, selectedGender == index
            )
        }
    }
}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}
