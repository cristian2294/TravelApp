package com.example.travelapp.login.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E8)),
    ) {
        val (
            icBackArrow,
            imgLogoApp,
            containerBody,
        ) = createRefs()

        BackArrowLoginScreen(
            navController,
            Modifier.padding(top = 16.dp).constrainAs(icBackArrow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )

        LogoApp(
            Modifier.constrainAs(imgLogoApp) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            },
        )

        BodyLogIn(
            Modifier.constrainAs(containerBody) {
                top.linkTo(imgLogoApp.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
    }
}

@Composable
fun BackArrowLoginScreen(navController: NavController, modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back arrow",
        modifier = modifier
            .padding(start = 16.dp)
            .clickable { navController.popBackStack() },
    )
}

@Composable
fun BodyLogIn(
    modifier: Modifier,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        EmailComponent(email, modifier) { email = it }
        Spacer(modifier = modifier.padding(top = 12.dp))
        PasswordComponent(password, modifier) { password = it }
        Spacer(modifier = modifier.padding(top = 48.dp))
        BtnLogIn(modifier)
        Spacer(modifier = modifier.padding(top = 8.dp))
        ForgotPassword(modifier)
        Spacer(modifier = modifier.padding(top = 24.dp))
        ContinueWithComponent(modifier)
        Spacer(modifier = modifier.padding(top = 24.dp))
        SocialMediaAccount(modifier)
    }
}

@Composable
fun BtnLogIn(modifier: Modifier) {
    Button(
        onClick = {
            // implement login logic
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9A826),
        ),
        elevation = ButtonDefaults.buttonElevation(8.dp),
    ) {
        Text(text = "Log In", fontSize = 14.sp)
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,

    ) {
        Text(
            text = "Forgot Password?",
            modifier = modifier.padding(end = 16.dp),
            fontSize = 14.sp,
            color = Color(0xFF382A12),
            fontWeight = FontWeight.Bold,
        )
    }
}
