package com.example.travelapp.login.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.commons.Routes

@Composable
fun StartScreen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF3E8))) {
        val (
            txtAppName,
            subtitleApp,
            imageSplash,
            btnSignUp,
            txtAlreadyHaveAccount,
            txtVersion,
        ) = createRefs()
        val startGuideLine = createGuidelineFromStart(0.1f)
        val endGuideLine = createGuidelineFromEnd(0.1f)
        LogoApp(
            Modifier.constrainAs(txtAppName) {
                top.linkTo(parent.top, margin = 8.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            },
        )

        ImageLogo(
            Modifier.constrainAs(imageSplash) {
                top.linkTo(txtAppName.bottom, margin = 2.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            },
        )

        SubtitleApp(
            Modifier.constrainAs(subtitleApp) {
                top.linkTo(imageSplash.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            },
        )

        ButtonSignUp(
            navController,
            Modifier.constrainAs(btnSignUp) {
                top.linkTo(subtitleApp.bottom, margin = 52.dp)
                start.linkTo(startGuideLine)
                end.linkTo(endGuideLine)
            },
        )

        AlreadyHaveAccount(
            navController,
            Modifier.constrainAs(txtAlreadyHaveAccount) {
                top.linkTo(btnSignUp.bottom, margin = 12.dp)
                start.linkTo(btnSignUp.start, margin = 16.dp)
                end.linkTo(btnSignUp.end, margin = 16.dp)
            },
        )

        VersionApp(
            Modifier.constrainAs(txtVersion) {
                start.linkTo(btnSignUp.start, margin = 16.dp)
                end.linkTo(btnSignUp.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 32.dp)
            },
        )
    }
}

@Composable
fun LogoApp(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_travel),
        contentDescription = "logo",
        modifier = modifier.size(200.dp),
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_explore_logo),
        contentDescription = "image Login",
        modifier = modifier
            .size(180.dp),
    )
}

@Composable
fun SubtitleApp(modifier: Modifier) {
    Text(
        text = "are you looking for new adventures?",
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF382A12),
    )
}

@Composable
fun ButtonSignUp(navController: NavController, modifier: Modifier) {
    Button(
        onClick = {
            navController.navigate(Routes.SignUpScreen.route)
        },
        modifier = modifier.width(300.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9A826),
        ),
        elevation = ButtonDefaults.buttonElevation(8.dp),
    ) {
        Text(text = "Sign Up", fontSize = 14.sp)
    }
}

@Composable
fun AlreadyHaveAccount(navController: NavController, modifier: Modifier) {
    Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Already have account?",
            modifier = modifier,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF382A12),
        )
        Text(
            text = "Log in",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF382A12),
            modifier = modifier
                .padding(start = 4.dp)
                .clickable { navController.navigate(Routes.LoginScreen.route) },
        )
    }
}

@Composable
fun VersionApp(modifier: Modifier) {
    Text(
        text = "V 1.0.0",
        modifier = modifier,
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF382A12),
    )
}
