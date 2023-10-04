package com.example.travelapp.forgotpassword.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.forgotpassword.presentation.ui.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    forgotPasswordViewModel: ForgotPasswordViewModel,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.orange_200)),
    ) {
        val (
            icBackArrow,
            containerBody,
        ) = createRefs()

        BackArrowForgotPasswordScreen(
            navController,
            Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16dp))
                .constrainAs(icBackArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )

        BodyForgotPassword(
            forgotPasswordViewModel,
            Modifier.constrainAs(containerBody) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
    }
}

@Composable
fun BackArrowForgotPasswordScreen(navController: NavController, modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back arrow",
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
            .clickable { navController.popBackStack() },
    )
}

@Composable
fun BodyForgotPassword(
    forgotPasswordViewModel: ForgotPasswordViewModel,
    modifier: Modifier,
) {
    val email by forgotPasswordViewModel.email.observeAsState(initial = "")
    Column(modifier = modifier) {
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_100dp)))
        TitleForgotPasswordComponent(modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_16dp)))
        DescriptionForgotPasswordComponent(modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_36dp)))
        EmailForgotPasswordComponent(email, modifier) {
            forgotPasswordViewModel.onEmailChanged(it)
        }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_24dp)))
        BtnForgotPasswordComponent(forgotPasswordViewModel, modifier)
    }
}

@Composable
fun TitleForgotPasswordComponent(modifier: Modifier) {
    Text(
        text = stringResource(R.string.forgot_password_title),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        color = colorResource(id = R.color.brown_700),
    )
}

@Composable
fun DescriptionForgotPasswordComponent(modifier: Modifier) {
    Text(
        text = stringResource(R.string.forgot_password_description),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        color = colorResource(id = R.color.brown_200),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailForgotPasswordComponent(
    email: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = email,
        label = {
            Text(
                text = stringResource(
                    id = R.string.sign_up_outlined_text_email,
                ),
                color = colorResource(id = R.color.brown_700),
            )
        },
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.brown_700),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = colorResource(id = R.color.brown_700),
            unfocusedLabelColor = Color.Gray,
        ),
    )
}

@Composable
fun BtnForgotPasswordComponent(
    forgotPasswordViewModel: ForgotPasswordViewModel,
    modifier: Modifier,
) {
    val context = LocalContext.current
    val isBtnContinueEnabled by forgotPasswordViewModel.isBtnContinueEnabled.observeAsState(initial = false)
    Button(
        onClick = {
            forgotPasswordViewModel.onResetPasswordClicked({
                Toast.makeText(
                    context,
                    R.string.forgot_password_message_reset_password_success,
                    Toast.LENGTH_SHORT,
                ).show()
            }, {
                Toast.makeText(
                    context,
                    R.string.forgot_password_message_reset_password_error,
                    Toast.LENGTH_SHORT,
                ).show()
            })
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_24dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.orange_900),
        ),
        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.dimen_8dp)),
        enabled = isBtnContinueEnabled,
    ) {
        Text(text = stringResource(id = R.string.forgot_password_button_continue), fontSize = 14.sp)
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}
*/
