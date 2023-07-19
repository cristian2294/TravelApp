package com.example.travelapp.login.presentation.ui.screens

import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.commons.Routes
import com.example.travelapp.login.presentation.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.orange_200)),
    ) {
        val (
            icBackArrow,
            imgLogoApp,
            containerBody,
        ) = createRefs()

        BackArrowLoginScreen(
            navController,
            Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16dp))
                .constrainAs(icBackArrow) {
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
            navController,
            loginViewModel,
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
            .padding(start = dimensionResource(id = R.dimen.dimen_16dp))
            .clickable { navController.popBackStack() },
    )
}

@Composable
fun BodyLogIn(
    navController: NavController,
    loginViewModel: LoginViewModel,
    modifier: Modifier,
) {
    val email by loginViewModel.email.observeAsState(initial = "")
    val password by loginViewModel.password.observeAsState(initial = "")
    Column(modifier = modifier) {
        EmailComponent(email, modifier) { loginViewModel.onLoginChanged(it, password) }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_12dp)))
        PasswordComponent(password, modifier) { loginViewModel.onLoginChanged(email, it) }
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_48dp)))
        BtnLogIn(navController, loginViewModel, modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_8dp)))
        ForgotPassword(modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_24dp)))
        ContinueWithComponent(modifier)
        Spacer(modifier = modifier.padding(top = dimensionResource(id = R.dimen.dimen_24dp)))
        SocialMediaAccount(modifier)
    }
}

@Composable
fun BtnLogIn(navController: NavController, loginViewModel: LoginViewModel, modifier: Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            loginViewModel.onLoginClickListener(
                {
                    navController.navigate(Routes.HomeScreen.route)
                },
                {
                    Toast.makeText(
                        context,
                        R.string.login_error_dialog_description,
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
    ) {
        Text(text = stringResource(id = R.string.login_button), fontSize = 14.sp)
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
            text = stringResource(id = R.string.login_forgot_password),
            modifier = modifier.padding(end = dimensionResource(id = R.dimen.dimen_16dp)),
            fontSize = 14.sp,
            color = colorResource(id = R.color.brown_700),
            fontWeight = FontWeight.Bold,
        )
    }
}
