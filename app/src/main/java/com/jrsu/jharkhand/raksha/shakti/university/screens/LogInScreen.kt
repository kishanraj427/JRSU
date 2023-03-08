package com.jrsu.jharkhand.raksha.shakti.university.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jrsu.jharkhand.raksha.shakti.university.Objects.AppFont
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.Objects.textFieldBG
import com.jrsu.jharkhand.raksha.shakti.university.R
import com.jrsu.jharkhand.raksha.shakti.university.Screen
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.Fire

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogInScreen(navController : NavController){

    val context = LocalContext.current
    var email by remember{ mutableStateOf("") }
    var pass by remember{ mutableStateOf("") }
    var passVis by remember{ mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var dialogOpen by remember{ mutableStateOf(false)}
    var buttonClick by remember{ mutableStateOf(false)}

    Colors.SetStatusColor(Colors.AppYellow)

    Column(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White, Color.LightGray
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box {
            Image(
                painterResource(id = R.drawable.ic_wave),
                "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(T.height(15f).dp)
            )
        }
        Image(
            painterResource(id = R.drawable.logo), "",
            Modifier.size(T.width(35f).dp)
        )
        Spacer(Modifier.height(T.height(5f).dp))
        Text(
            "Welcome Back",
            fontSize = T.width(8f).sp ,
            fontWeight = FontWeight.W600,
            fontFamily = AppFont.minigap
        )
        Spacer(Modifier.height(T.height(4f).dp))
        TextField(
            value = email,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = { email = it},
            shape = RoundedCornerShape(15.dp),
            colors = textFieldBG(),
            singleLine = true,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = pass,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = { pass = it},
            shape = RoundedCornerShape(15.dp),
            colors = textFieldBG(),
            label = { Text("Password") },
            visualTransformation = if (!passVis) PasswordVisualTransformation()
            else  VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}),
            trailingIcon = {
                val image = if (!passVis) Icons.Default.Visibility
                else Icons.Default.VisibilityOff
                IconButton(
                    onClick = { passVis = !passVis },
                    content = {
                        Icon(image , "")
                    }
                )
            }
        )
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(0.8f),
            Arrangement.End
        ){
            Text(
                "Forgot Password ?",
                fontSize = T.width(4f).sp,
                modifier = Modifier
                    .clickable {
                        dialogOpen = true
                    }
            )
        }
        if(dialogOpen)
            dialogOpen = showDialog()
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
                if(pass == "" || email == ""){
                    toast.setText("Fill the boxes properly")
                    toast.show()
                }
                else{
                    if(pass.length < 6){
                        toast.setText("Password length must be greater than 5")
                        toast.show()
                    }
                    else{
                        buttonClick = true
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener {
                                Fire.firebaseAuth = it.user
                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.LogInScreen.route){ inclusive = true }
                                }
                            }
                            .addOnFailureListener {
                                toast.setText(it.message)
                                toast.show()
                                buttonClick = false
                            }
                    }
                }
            },
            elevation = ButtonDefaults.elevation(0.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(T.width(10f).dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Colors.AppOrange
            )
        ) {
            if(buttonClick){
                CircularProgressIndicator(Modifier.size(T.width(4f).dp), Color.White, 2.dp)
            }else{
                Text(
                    "LogIn",
                    fontFamily = AppFont.mPlus,
                    fontWeight = FontWeight.Bold,
                    fontSize = T.width(4f).sp,
                    color = Color.White
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Text(
            buildAnnotatedString {
                append("Don't have an account?")
                withStyle(style = SpanStyle(
                    color = Colors.AppBlue,
                    fontWeight = FontWeight.W500
                )){ append(" Sign up")}
            },
            fontSize = T.width(4f).sp,
            modifier = Modifier.clickable {
                navController.navigate(Screen.SingUpScreen.route)
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun showDialog(): Boolean{
    val keyboardController = LocalSoftwareKeyboardController.current
    var openDialog by remember{ mutableStateOf(true)}
    val context = LocalContext.current
    var email by remember { mutableStateOf("")}
    if(openDialog){
        AlertDialog(
            shape = RoundedCornerShape(10.dp),
            onDismissRequest = { openDialog = false },
            title = {Text("Forgot your password ?", fontWeight = FontWeight.W600, color = Colors.AppOrange)},
            text = {
                   Column{
                       Text("Please enter your email to request a password reset.", fontWeight = FontWeight.W500)
                       Spacer(Modifier.height(10.dp))
                       OutlinedTextField(
                           value = email,
                           modifier = Modifier.fillMaxWidth(),
                           onValueChange = { email = it },
                           shape = RoundedCornerShape(15.dp),
                           colors = TextFieldDefaults.outlinedTextFieldColors(
                               backgroundColor = Color.Black,
                               focusedBorderColor = Color.Black,
                               unfocusedBorderColor = Color.Black,
                               textColor = Color.Black,
                               cursorColor = Color.Black
                           ),
                           singleLine = true,
                           placeholder = { Text("Email") },
                           keyboardOptions = KeyboardOptions(
                               imeAction = ImeAction.Done,
                               keyboardType = KeyboardType.Email
                           ),
                           keyboardActions = KeyboardActions(
                               onDone = {keyboardController?.hide()})
                       )
                   }
            },
            contentColor = Color.Black,
            backgroundColor = Color.White,
            confirmButton = {
                TextButton(onClick = {
                    val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
                    if(email != ""){
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnSuccessListener {
                            toast.setText("Password Reset Requested.")
                            toast.show()
                        }.addOnFailureListener {
                            toast.setText(it.message)
                            toast.show()
                        }
                        openDialog = false
                    }
                    else {
                        toast.setText("Please enter your email.")
                        toast.show()
                    }
                }) {
                    Text("Reset", color = Color.Black)
                }
            },
        )
    }
    return openDialog
}
