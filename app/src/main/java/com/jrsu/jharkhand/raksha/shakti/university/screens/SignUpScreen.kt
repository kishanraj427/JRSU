package com.jrsu.jharkhand.raksha.shakti.university.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
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
fun SignUpScreen(navController : NavController){

    var buttonClick by remember{ mutableStateOf(false)}
    val context = LocalContext.current
    var email by remember{ mutableStateOf("") }
    var pass by remember{ mutableStateOf("") }
    var course by remember{ mutableStateOf("Select Course") }
    var name by remember{ mutableStateOf("") }
    var passVis by remember{ mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val courseList = listOf(
        "B.Sc. in Computer Application & Cyber Security",
        "B.Sc. in Forensic Science",
        "B.B.A. in Security Management",
        "M.A./M.Sc. in Criminology",
        "M.Sc. in Forensic Science",
        "Diploma in Police Science",
        "Certificate Course in Police Science",
        "P.G. Diploma in Disaster Management",
        "P.G. Diploma in Industrial Security"
    )
    var expanded by remember{ mutableStateOf(false)}
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White, Color.LightGray
                    )
                )
            )
            .scrollable(scrollState, Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                    "Create Account",
                    fontSize = T.width(8f).sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = AppFont.minigap
                )
                Spacer(Modifier.height(T.height(4f).dp))
                Box{
                    Button(
                        shape = RoundedCornerShape(15.dp),
                        onClick = { expanded = true},
                        elevation = ButtonDefaults.elevation(0.dp),
                        colors = ButtonDefaults.buttonColors(Color.White,Color.Black ),
                        modifier = Modifier.fillMaxWidth(0.8f),
                    ) {
                        Row{ Text("$course  ")
                            Icon(Icons.Default.ArrowDropDown,"")}
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        courseList.forEach {
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    course = it
                                }
                            ) {
                                Text(it)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                TextField(
                    value = name,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onValueChange = {
                        name = it
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = textFieldBG(),
                    singleLine = true,
                    label = { Text("Name") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()})
                )
                Spacer(Modifier.height(10.dp))
                TextField(
                    value = email,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onValueChange = { email = it },
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
                    onValueChange = { pass = it },
                    shape = RoundedCornerShape(15.dp),
                    colors = textFieldBG(),
                    label = { Text("Password") },
                    visualTransformation = if (!passVis) PasswordVisualTransformation()
                    else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()}),
                    trailingIcon = {
                        val image = if (!passVis) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff
                        IconButton(
                            onClick = { passVis = !passVis },
                            content = {
                                Icon(image, "")
                            }
                        )
                    }
                )
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
                        if(course == "Select Course"){
                            toast.setText("Please select Course")
                            toast.show()
                        }
                        else if(pass == "" || name == "" || email == ""){
                            toast.setText("Fill the boxes properly")
                            toast.show()
                        }
                        else{
                            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                toast.setText("Invalid email type")
                                toast.show()
                            }
                            else if(pass.length < 6){
                                toast.setText("Password length must be greater than 5.")
                                toast.show()
                            }
                            else{
                                buttonClick = true
                                val map = mutableMapOf<String, String>()
                                map["name"] = name
                                map["email"] = email
                                map["course"] = course
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                                    .addOnSuccessListener {
                                        val uid = it.user?.uid
                                        if (uid != null) {
                                            Fire.firebaseAuth = it.user
                                            Fire.firebaseStore.collection("Users").document(uid).set(map)
                                            navController.navigate(Screen.HomeScreen.route) {
                                                popUpTo(Screen.SingUpScreen.route){ inclusive = true }
                                                popUpTo(Screen.LogInScreen.route){ inclusive = true }
                                            }
                                        }}
                                    .addOnFailureListener {
                                        toast.setText(it.message)
                                        toast.show()
                                        buttonClick = false
                                    }
                            }
                        }
                    },
                    elevation = ButtonDefaults.elevation(0.dp),
                    modifier = Modifier.fillMaxWidth(0.8f).height(T.width(10f).dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        Colors.AppOrange
                    )
                ) {
                    if(buttonClick){
                        CircularProgressIndicator(Modifier.size(T.width(4f).dp), Color.White, 2.dp)
                    }else{
                        Text(
                            "SignUp",
                            fontFamily = AppFont.mPlus,
                            fontWeight = FontWeight.Bold,
                            fontSize = T.width(4f).sp,
                            color = Color.White
                        )
                    }

                }
            }
}

