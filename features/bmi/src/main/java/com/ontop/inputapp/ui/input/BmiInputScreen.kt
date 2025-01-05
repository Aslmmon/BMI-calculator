package com.ontop.inputapp.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.heightVariants
import com.ontop.inputapp.R
import com.ontop.inputapp.shared.AgeContent
import com.ontop.inputapp.shared.ButtonWithHyperLinkContent
import com.ontop.inputapp.shared.ContentWithTitle
import com.ontop.inputapp.shared.GenderContent
import com.ontop.inputapp.shared.HeightContent
import com.ontop.inputapp.shared.SharedViewModel
import com.ontop.inputapp.shared.VariantContent
import com.ontop.inputapp.shared.TitleScreen
import com.ontop.inputapp.shared.WeightContent
import com.ontop.weightVariants


@Composable
fun BmiInputScreen(
    userInputViewModel: UserInputViewModel = hiltViewModel(),
    onCalculateClicked: () -> Unit = {},
    sharedViewModel: SharedViewModel<UserState.UserData>
) {
    val scrollState = rememberScrollState()
    val userData by userInputViewModel.userData.collectAsState()
    val loader by userInputViewModel.loading.collectAsState()



    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TitleScreen(stringResource(id = R.string.bmi_calculator_title))
        Spacer(modifier = Modifier.height(10.dp))
        ContentWithTitle(title = stringResource(R.string.gender_title), content = {
            GenderContent { userInputViewModel.updateUserData(gender = it) }
        })
        ContentWithTitle(title = stringResource(R.string.age_title), content = {
            AgeContent(
                modifier = Modifier,
                onAgeChosen = { userInputViewModel.updateUserData(age = it) })
        })

        ContentWithTitle(title = stringResource(R.string.height), content = {
            HeightContent { userInputViewModel.updateUserData(height = it) }
        }, showContentVariants = true, variants = {
            VariantContent(
                heightVariants,
                variantChosen = { userInputViewModel.updateUserData(heightVariant = it) })
        })

        ContentWithTitle(title = stringResource(R.string.weight), content = {
            WeightContent(modifier = Modifier) { userInputViewModel.updateUserData(weight = it) }
        }, showContentVariants = true, variants = {
            VariantContent(weightVariants, variantChosen =
            { userInputViewModel.updateUserData(weightVariant = it) })

        })

    }
    ButtonWithHyperLinkContent(
        buttonText = R.string.calculate_bmi,
        isLoading = loader,
        onCalculateClicked = {
            sharedViewModel.setData(userData)
            userInputViewModel.showLoading()
            onCalculateClicked.invoke()
        }
    )

}

