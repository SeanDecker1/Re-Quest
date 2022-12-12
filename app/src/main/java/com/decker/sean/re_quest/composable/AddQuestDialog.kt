package com.decker.sean.re_quest.composable

import android.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.models.QuestViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddQuestDialog(questViewModel: QuestViewModel, setShowDialog: (Boolean) -> Unit) {

    var questNameTxtFieldError by remember { mutableStateOf("") }
    var questDescriptionTxtFieldError by remember { mutableStateOf("") }
    var questNameTxtField by remember { mutableStateOf("") }
    var questDescriptionTxtField by remember { mutableStateOf("") }

    val radioOptions = listOf("Yes", "No")
    var selectedRadioItem by remember {
        mutableStateOf(radioOptions[0])
    }
    var selectedVisibility = 0

    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )

    ) {

        Surface(
            shape = RoundedCornerShape(5.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ){

            //Main Column
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                //Column With New Quest Bar and Text Fields
                //The .weight(1f, false) is an important property so this column doesn't override the bottom save button
                Column(modifier = Modifier
                    .padding(10.dp)
                    .weight(1f, false)) {

                    //New Quest and Close Button top Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //New Quest with Quest Icon Row
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(color = Color.White),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Outlined.HistoryEdu,
                                contentDescription = "Quest",
                                modifier = Modifier
                                    .padding(0.dp)
                                    .size(30.dp),
                            ) // Ends Icon

                            Text(
                                text = "New Quest",
                                modifier = Modifier.padding(5.dp),
                                fontSize = 24.sp
                            ) // Ends Text
                        } // Ends Row

                        IconButton(onClick = { setShowDialog(false) }) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                            )
                        }
                    } // Ends top Row

                    //Text Fields Column
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        // Quest name text field
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            isError = questNameTxtFieldError.isNotEmpty(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.TextFields,
                                    contentDescription = "",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                ) // Ends Icon
                            },
                            placeholder = { Text(text = questNameTxtFieldError) },
                            label = { Text(text = "Quest Name", style = TextStyle(color = Color.Black)) },
                            value = questNameTxtField,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                questNameTxtField = it
                            }
                        ) // Ends quest name text field

                        Spacer(modifier = Modifier.height(20.dp))

                        // Quest description text field
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            isError = questDescriptionTxtFieldError.isNotEmpty(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.TextFields,
                                    contentDescription = "",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                ) // Ends Icon
                            },
                            placeholder = { Text(text = questDescriptionTxtFieldError) },
                            label = { Text(text = "Quest Description", style = TextStyle(color = Color.Black)) },
                            value = questDescriptionTxtField,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                questDescriptionTxtField = it
                            },
                            singleLine = false,
                            minLines = 3
                        ) // Ends quest description text field

                        Spacer(modifier = Modifier.height(20.dp))

                        // Visibility radio buttons
                        Column(modifier = Modifier.selectableGroup()) {

                            Text(
                                text = "Visible:",
                                modifier = Modifier.padding(5.dp),
                                fontSize = 24.sp
                            ) // Ends Text

                            radioOptions.forEach { label ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .selectable(
                                            selected = (selectedRadioItem == label),
                                            onClick = { selectedRadioItem = label },
                                            role = Role.RadioButton
                                        )
                                        .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        modifier = Modifier.padding(end = 16.dp),
                                        selected = (selectedRadioItem == label),
                                        onClick = null // null recommended for accessibility with screen readers
                                    )
                                    Text(text = label)
                                } // Row
                            } // Ends radioOptions.forEach
                        } // Ends radio button column

                    } // Ends Column of Text Fields and Radio Buttons

                } // Ends Column with Top Row and Text Fields

                //Save Quest Button Box
                Box(modifier = Modifier
                    .padding(40.dp, 0.dp, 40.dp, 10.dp)
                    .height(50.dp)) {

                    Button(
                        onClick = {

                            // Reset error messages
                            questNameTxtFieldError = ""
                            questDescriptionTxtFieldError = ""

                            // Validate quest name
                            if (!checkQuestName(questNameTxtField)) {
                                questNameTxtFieldError = "Enter a valid input for quest name."
                            } // Ends if

                            // Validate quest description
                            if (!checkQuestDescription(questDescriptionTxtField)) {
                                questDescriptionTxtFieldError = "Enter a valid input for quest description."
                            } // Ends if

                            if (questNameTxtFieldError.isNotEmpty() || questDescriptionTxtFieldError.isNotEmpty()) {
                                return@Button
                            } // Ends if

                            if (selectedRadioItem == "Yes") {
                                selectedVisibility = 1
                            } else if (selectedRadioItem == "No") {
                                selectedVisibility = 0
                            } // Ends if

                            // Create and insert quest
                            questViewModel.insertQuest(
                                Quest(
                                    quest_name = questNameTxtField,
                                    quest_description = questDescriptionTxtField,
                                    quest_visible = selectedVisibility,
                                    quest_completed = 0
                                )
                            ) // Ends insert
                            setShowDialog(false)

                        }, // Ends onClick
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Save Quest")
                    } // Ends Button

                } // Ends Box w/ Save Button

            } // Ends Main Column

        } // Ends Surface

    } // Ends Dialog

} // Ends AddQuestDialog

fun checkQuestName(name: String): Boolean = name.split(' ').all { it.isNotEmpty() }


fun checkQuestDescription(description: String): Boolean = description.split(' ').all { it.isNotEmpty() }