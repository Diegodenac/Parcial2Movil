package com.example.myapplication.feature.login.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun LoginScreen( onSuccess: () -> Unit) {

    var userSignIn by remember { mutableStateOf("") }
    var passwordSignIn by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = stringResource(R.string.sign_in_button ))

        TextField(
            label = {
                Text(text = stringResource(R.string.email_address_label ))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            placeholder = {
                Text(text = stringResource(R.string.email_address_field ))
            },
            leadingIcon = {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp),
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors =  TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Black),
            value = userSignIn, onValueChange = { userSignIn = it}
        )
        TextField(
            label = {
                Text(text = stringResource(R.string.password_label ))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            placeholder = {
                Text(text = stringResource(R.string.password_field ))
            },
            leadingIcon = {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp),
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors =  TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Black),
            value = passwordSignIn, onValueChange = { passwordSignIn = it})
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { onSuccess() },
//            colors = ButtonDefaults.colors(
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Black)
        ) {
            Text(text = stringResource(R.string.sign_in_button ))
        }
        //Text(passwordSignIn.toString())
    }
}

