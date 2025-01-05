package com.fitform.bmi.userinput

import com.ontop.Variants
import com.ontop.inputapp.ui.input.UserInputViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserInputViewModelTest {

    private lateinit var viewModel: UserInputViewModel

    @Before
    fun setUp() {
        viewModel = UserInputViewModel()
    }

    @Test
    fun testUpdateUserData_age() = runTest {
        viewModel.updateUserData(age = 30)
        Assert.assertEquals(30, viewModel.userData.value.age)
    }

    @Test
    fun testUpdateUserData_gender() = runTest {
        viewModel.updateUserData(gender = 1)
        Assert.assertEquals(1, viewModel.userData.value.gender)
    }

    @Test
    fun testUpdateUserData_height() = runTest {
        viewModel.updateUserData(height = 175)
        Assert.assertEquals(175, viewModel.userData.value.height)
    }

    @Test
    fun testUpdateUserData_weight() = runTest {
        viewModel.updateUserData(weight = 70)
        Assert.assertEquals(70, viewModel.userData.value.weight)
    }

    @Test
    fun testUpdateUserData_heightVariant() = runTest {
        viewModel.updateUserData(heightVariant = Variants.Inches)
        assertEquals(Variants.Inches, viewModel.userData.value.heightVariantType)
    }

    @Test
    fun testUpdateUserData_weightVariant() = runTest {
        viewModel.updateUserData(weightVariant = Variants.KG)
        assertEquals(Variants.KG, viewModel.userData.value.weightVariantType)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testShowLoading() = runBlocking {
        val mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel.showLoading()
        assertTrue(viewModel.loading.value)
        delay(2550)
        assertFalse(viewModel.loading.value)
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


}