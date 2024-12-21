package com.ontop.inputapp.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.heightVariants
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.AgeContent
import com.ontop.inputapp.shared_ui.ButtonWithHyperLinkContent
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderContent
import com.ontop.inputapp.shared_ui.HeightContent
import com.ontop.inputapp.shared_ui.VariantContent
import com.ontop.inputapp.shared_ui.TitleScreen
import com.ontop.inputapp.shared_ui.WeightContent
import com.ontop.inputapp.shared_ui._gap
import com.ontop.weightVariants


@Composable
fun BmiInputScreen(
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel(),
    onCalculateClicked: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TitleScreen(stringResource(id = R.string.bmi_calculator_title))


        _gap(height = 10)
        ContentWithTitle(title = stringResource(R.string.gender_title), content = {
            GenderContent()

        })
        ContentWithTitle(title = stringResource(R.string.age_title), content = { AgeContent() })


        ContentWithTitle(title = stringResource(R.string.height), content = {
            HeightContent()
        }, showContentVariants = true, variants = {
            VariantContent(heightVariants)
        })


        ContentWithTitle(title = stringResource(R.string.weight), content = {
            WeightContent()
        }, showContentVariants = true, variants = {
            VariantContent(weightVariants)

        })

    }
    ButtonWithHyperLinkContent(buttonText = R.string.calculate_bmi, onCalculateClicked)

}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}
