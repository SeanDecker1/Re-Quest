package com.decker.sean.re_quest.composable

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task
import com.decker.sean.re_quest.models.QuestViewModel

@Composable
fun AddQuestTaskDialog(questViewModel: QuestViewModel, setShowDialog: (Boolean) -> Unit, currentQuestId: Int) {

    // task_id
    // task_name
    // task_description
    // task_quest
    // task_visible
    // task_completed

    val taskNameTxtFieldError = remember { mutableStateOf("Invalid input for task name.") }
    val taskDescriptionTxtFieldError = remember { mutableStateOf("Invalid input for task description.") }
    val taskNameTxtField = remember { mutableStateOf("") }
    val taskDescriptionTxtField = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { setShowDialog(false) }) {

        Surface(
            shape = RoundedCornerShape(5.dp),
            color = Color.White
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "New Task",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        ) // Ends Text

                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = colorResource(R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        ) // Ends Icon

                    } // Ends Row

                    Spacer(modifier = Modifier.height(20.dp))

                    // Quest name text field
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = if (taskNameTxtFieldError.value.isEmpty()) Color.Green else Color.Red
                                ),
                                shape = RoundedCornerShape(10)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
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
                        placeholder = { Text(text = taskNameTxtFieldError.value) },
                        value = taskNameTxtField.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = {
                            taskNameTxtField.value = it
                        }
                    ) // Ends quest name text field

                    Spacer(modifier = Modifier.height(20.dp))

                    // Quest description text field
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = if (taskDescriptionTxtFieldError.value.isEmpty()) Color.Green else Color.Red
                                ),
                                shape = RoundedCornerShape(10)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
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
                        placeholder = { Text(text = taskDescriptionTxtFieldError.value) },
                        value = taskDescriptionTxtField.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = {
                            taskDescriptionTxtField.value = it
                        }
                    ) // Ends quest description text field

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

                        Button(
                            onClick = {

                                // Reset error messages
                                taskNameTxtFieldError.value = ""
                                taskDescriptionTxtFieldError.value = ""

                                // Validate quest name
                                if (!checkQuestName(taskNameTxtField.value)) {
                                    taskNameTxtFieldError.value = "Invalid input for task name."
                                } // Ends if

                                // Validate quest description
                                if (!checkQuestDescription(taskDescriptionTxtField.value)) {
                                    taskDescriptionTxtFieldError.value = "Invalid input for task description."
                                } // Ends if

                                if (taskNameTxtFieldError.value.isNotEmpty() || taskDescriptionTxtFieldError.value.isNotEmpty()) {
                                    return@Button
                                } // Ends if

                                // Create and insert task
                                questViewModel.insertTask(
                                    Task(
                                        task_name = taskNameTxtField.value,
                                        task_description = taskDescriptionTxtField.value,
                                        task_quest = currentQuestId,
                                        task_visible = 0,
                                        task_completed = 0
                                    )
                                ) // Ends insert
                                setShowDialog(false)

                            }, // Ends onClick
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Save Task")
                        } // Ends Button

                    } // Ends Box

                } // Ends Column

            } // Ends Box

        } // Ends Surface

    } // Ends Dialog

} // Ends AddQuestDialog

fun checkTaskName(name: String): Boolean = name.split(' ').all { !it.isEmpty() && it[0].isUpperCase() }

fun checkTaskDescription(description: String): Boolean = description.split(' ').all { !it.isEmpty() && it[0].isUpperCase() }