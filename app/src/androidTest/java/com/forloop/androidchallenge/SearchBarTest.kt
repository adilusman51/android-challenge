package com.forloop.codechallenge

import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.input.TextFieldValue
import com.forloop.codechallenge.presentation.component.SearchBar
import com.forloop.codechallenge.presentation.theme.CodeChallengeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val activity by lazy { composeTestRule.activity }

    private val searchState = mutableStateOf(TextFieldValue(""))

    @Before
    fun setup() {
        composeTestRule.setContent {
            CodeChallengeTheme {
                SearchBar(
                    state = searchState,
                    onChangeText = {
                        searchState.value = it
                    },
                    onClearText = {
                        searchState.value = TextFieldValue("")
                    })
            }
        }
//        composeTestRule.onRoot(useUnmergedTree = true).printToLog("textFieldExits")
    }

    @Test
    fun searchbar_Exists() {
        findSearchBar().assertExists()
    }

    @Test
    fun searchbar_textFieldExists() {
        findTextInputField().assertExists().assertIsDisplayed().assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun searchbar_searchIconExists() {
        findSearchIcon().assertExists().assertHasNoClickAction()
    }

    @Test
    fun searchbar_clearButtonExists() {
        findClearButton().assertExists().assertHasClickAction()
    }

    @Test
    fun searchbar_clearTextWorking() {
        findTextInputField(true).performTextInput(activity.getString(R.string.test_text_input))
        findTextInputField(true).assertTextEquals(activity.getString(R.string.test_text_input))
        assert(searchState.value.text == activity.getString(R.string.test_text_input))
        findClearButton().performClick()
        findTextInputField(true).assertTextEquals("")
        assert(searchState.value.text == "")
    }

    private fun findSearchBar() =
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.searchbar_desc))

    private fun findTextInputField(useUnmergedTree: Boolean = false): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasSetTextAction() and
                    hasAnyAncestor(
                        hasContentDescription(activity.getString(R.string.searchbar_desc))
                    ), useUnmergedTree = useUnmergedTree
        )
    }

    private fun findSearchIcon(): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasContentDescription(activity.getString(R.string.search_icon)) and
                    hasAnyAncestor(
                        hasContentDescription(activity.getString(R.string.searchbar_desc)),
                    ),
            true
        )
    }

    private fun findClearButton(): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasContentDescription(activity.getString(R.string.clear_button_desc)) and
                    hasAnyAncestor(
                        hasContentDescription(activity.getString(R.string.searchbar_desc)),
                    ),
            true
        )
    }
}