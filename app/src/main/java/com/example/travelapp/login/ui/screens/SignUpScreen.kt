package com.example.travelapp.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

@Composable
fun SignUpScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E8)),
    ) {
        val (
            icBackArrow,
            txvTitleScreen,
            containerBody,
            txvAlreadyHaveAccount,
        ) = createRefs()

        BackArrow(
            navController,
            Modifier.padding(top = 16.dp).constrainAs(icBackArrow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )

        TxvTitleScreen(
            Modifier.padding(top = 16.dp).constrainAs(txvTitleScreen) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
        BodySignUp(
            Modifier.constrainAs(containerBody) {
                top.linkTo(txvTitleScreen.bottom, margin = 48.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
        AlreadyHaveAccount(
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
            .padding(start = 16.dp)
            .clickable { navController.popBackStack() },
    )
}

@Composable
fun TxvTitleScreen(modifier: Modifier) {
    Text(
        text = "Sign Up",
        textAlign = TextAlign.Center,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF382A12),
    )
}

@Composable
fun BodySignUp(
    modifier: Modifier,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        NameComponent(name, modifier) { name = it }
        Spacer(modifier = modifier.padding(top = 12.dp))
        EmailComponent(email, modifier) { email = it }
        Spacer(modifier = modifier.padding(top = 12.dp))
        PasswordComponent(password, modifier) { password = it }
        Spacer(modifier = modifier.padding(top = 48.dp))
        BtnCreateAccount(modifier)
        Spacer(modifier = modifier.padding(top = 24.dp))
        ContinueWithComponent(modifier)
        Spacer(modifier = modifier.padding(top = 24.dp))
        SocialMediaAccount(modifier)
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
            Text(text = "Name", color = Color(0xFF382A12))
        },
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        singleLine = true,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF382A12),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color(0xFF382A12),
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
            Text(text = "Email", color = Color(0xFF382A12))
        },
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF382A12),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color(0xFF382A12),
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
            Text(text = "Password", color = Color(0xFF382A12))
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { onValueChange(it) },
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF382A12),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color(0xFF382A12),
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
                    tint = Color(0xFF382A12),
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
fun BtnCreateAccount(modifier: Modifier) {
    Button(
        onClick = {
            // implement create account logic
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9A826),
        ),
        elevation = ButtonDefaults.buttonElevation(8.dp),
    ) {
        Text(text = "Create Account", fontSize = 14.sp)
    }
}

@Composable
fun ContinueWithComponent(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(
            modifier = Modifier
                .background(Color(0xFF382A12))
                .height(1.dp)
                .weight(1f),
        )
        Text(
            text = "Or continue with",
            Modifier.padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF382A12),
        )
        Divider(
            modifier = Modifier
                .background(Color(0xFF382A12))
                .height(1.dp)
                .weight(1f),
        )
    }
}

@Composable
fun SocialMediaAccount(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { }, modifier = modifier.padding(end = 16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Login with Google",
            )
        }
        IconButton(onClick = { }, modifier = modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Login with Facebook",
            )
        }
    }
}
