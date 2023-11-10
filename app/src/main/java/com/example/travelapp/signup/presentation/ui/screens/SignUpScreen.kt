package com.example.travelapp.signup.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.commons.Routes
import com.example.travelapp.login.presentation.ui.screens.AlreadyHaveAccount
import com.example.travelapp.login.presentation.ui.screens.SocialMediaAccount
import com.example.travelapp.login.presentation.ui.viewmodel.LoginViewModel
import com.example.travelapp.signup.presentation.ui.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.orange_200)),
    ) {
        val (
            icBackArrow,
            txvTitleScreen,
            containerBody,
            txvAlreadyHaveAccount,
        ) = createRefs()

        BackArrow(
            navController,
            Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16dp))
                .constrainAs(icBackArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )

        TxvTitleScreen(
            Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16dp))
                .constrainAs(txvTitleScreen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
        BodySignUp(
            signUpViewModel,
            loginViewModel,
            navController,
            Modifier.constrainAs(containerBody) {
                top.linkTo(txvTitleScreen.bottom, margin = 48.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
        AlreadyHaveAccount(
            navController,
            Modifier.constrainAs(txvAlreadyHaveAccount) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(containerBody.bottom, margin = 32.dp)
            },
        )
    }
}

@Composable
fun BackArrow(navController: NavController, modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back arrow",
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
            .clickable { navController.popBackStack() },
    )
}

@Composable
fun TxvTitleScreen(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.sign_up_title),
        textAlign = TextAlign.Center,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        color = colorResource(id = R.color.brown_700),
    )
}

@Composable
fun BodySignUp(
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController,
    modifier: Modifier,
) {
    val name by signUpViewModel.name.observeAsState(initial = "")
    val email by signUpViewModel.email.observeAsState(initial = "")
    val password by signUpViewModel.password.observeAsState(initial = "")
    Column(modifier = modifier) {
        NameComponent(name, modifier) {
            signUpViewModel.onSignUpChanged(
                it,
                email,
                password,
            )
        }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_12dp)))
        EmailComponent(email, modifier) {
            signUpViewModel.onSignUpChanged(
                name,
                it,
                password,
            )
        }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_12dp)))
        PasswordComponent(password, modifier) {
            signUpViewModel.onSignUpChanged(
                name,
                email,
                it,
            )
        }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_48dp)))
        BtnCreateAccount(
            signUpViewModel,
            navController,
            modifier,
        )
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_24dp)))
        ContinueWithComponent(modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_24dp)))
        SocialMediaAccount(modifier, loginViewModel, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameComponent(
    name: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = name,
        label = {
            Text(
                text = stringResource(
                    id = R.string.sign_up_outlined_text_name,
                ),
                color = colorResource(id = R.color.brown_700),
            )
        },
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        singleLine = true,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailComponent(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordComponent(
    password: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        label = {
            Text(
                text = stringResource(id = R.string.sign_up_outlined_text_password),
                color = colorResource(id = R.color.brown_700),
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_16dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.brown_700),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = colorResource(id = R.color.brown_700),
            unfocusedLabelColor = Color.Gray,
        ),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) {
                        Icons.Filled.VisibilityOff
                    } else {
                        Icons.Filled.Visibility
                    },
                    contentDescription = "show password",
                    tint = colorResource(id = R.color.brown_700),
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}

@Composable
fun BtnCreateAccount(
    signUpViewModel: SignUpViewModel,
    navController: NavController,
    modifier: Modifier,
) {
    val isLoginEnabled by signUpViewModel.isLoginEnabled.observeAsState(initial = false)
    val context = LocalContext.current
    Button(
        onClick = {
            signUpViewModel.onSignUpClickListener(
                { navController.navigate(Routes.HomeScreen.route) },
                {
                    Toast.makeText(
                        context,
                        R.string.sign_up_error_dialog_description,
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_24dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.orange_900),
        ),
        elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.dimen_8dp)),
        enabled = isLoginEnabled,
    ) {
        Text(text = stringResource(id = R.string.sign_up_button_create_account), fontSize = 14.sp)
    }
}

@Composable
fun ContinueWithComponent(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_24dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(
            modifier = Modifier
                .background(colorResource(id = R.color.brown_700))
                .height(dimensionResource(id = R.dimen.dimen_1dp))
                .weight(1f),
        )
        Text(
            text = "Or continue with",
            Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8dp)),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.brown_700),
        )
        Divider(
            modifier = Modifier
                .background(colorResource(id = R.color.brown_700))
                .height(dimensionResource(id = R.dimen.dimen_1dp))
                .weight(1f),
        )
    }
}
