package com.tnt.food_delivery.presentation.sign_up_process

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.components.ShowLoading
import com.tnt.food_delivery.core.components.showToast
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.ui.components.BackButton
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpProcessScreen(
    navController: NavController,
    username: String,
    email: String,
    password: String,
    viewModel: SignUpProcessViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.register.collectAsState()
    val calendar = Calendar.getInstance()

    LaunchedEffect(state) {
        Log.d("check", "ok")
        when (state.status) {
            EventStatus.SUCCESS -> {
                navController.navigate(NavDestinations.SIGNUP_SUCCESS_SCREEN) {
                    launchSingleTop = true
                    popUpTo(0) { inclusive = true }
                }
                Log.d("sign up process data", state.data.toString())
            }

            EventStatus.LOADING -> {
                Log.d("Loading", "Loading")
            }

            EventStatus.ERROR -> {
                Log.d("Error", state.error ?: "Lỗi bất định")
                showToast(context, state.error ?: "Lỗi bất định")
            }

            else -> {}
        }
    }

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            viewModel.birthday.value = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
    )
    datePicker.datePicker.maxDate = calendar.timeInMillis

    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.triangle_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                BackButton {
                    navController.popBackStack()
                }
                Text(
                    modifier = Modifier.padding(end = 60.dp),
                    text = "Fill in your bio to get started",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(end = 90.dp),
                    text = "This data will be displayed in your account profile for security",
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    value = viewModel.fullname.value,
                    onValueChange = { value ->
                        viewModel.fullname.value = value
                        viewModel.validateFullName()
                    },
                    hintText = "Full Name",
                    error = viewModel.fullNameErrMsg.value,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    value = viewModel.birthday.value,
                    onValueChange = { value -> viewModel.birthday.value = value },
                    hintText = "Birthday",
                    onClick = { datePicker.show() },
                    error = viewModel.birthdayErrMsg.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    value = viewModel.phoneNumber.value,
                    onValueChange = { value ->
                        viewModel.phoneNumber.value = value
                        viewModel.validatePhoneNumber()
                    },
                    hintText = "Mobile Number",
                    error = viewModel.phoneNumberErrMsg.value,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Phone
                    )
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GradientButton(
                        modifier = Modifier
                            .height(56.dp)
                            .width(157.dp),
                        text = "Next",
                        onClick = {
                            coroutineScope.launch { viewModel.signup(username, email, password) }
                        },
                        isEnable = viewModel.isEnableButton.value
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                if (state.status == EventStatus.LOADING) ShowLoading()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    hintText: String,
    error: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onClick: (() -> Unit)? = null,
) {
    val source = remember { MutableInteractionSource() }

    if (source.collectIsPressedAsState().value) onClick?.let { it() }

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                    spread = 25.dp,
                    blurRadius = 60.dp
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                containerColor = Color.White,
            ),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = hintText, color = Color(0xFF3B3B3B)) },
            shape = RoundedCornerShape(22),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            isError = error.isNotEmpty(),
            readOnly = onClick != null,
            interactionSource = source
        )
        if (error.isNotEmpty())
            Text(
                modifier = Modifier.padding(start = 35.dp),
                text = error,
                fontSize = 12.sp,
                color = Color.Red
            )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpProcessPreview() {
    FoodDeliveryTheme {
        SignUpProcessScreen(
            rememberNavController(), "", "", ""
        )
    }
}