package com.jrsu.jharkhand.raksha.shakti.university.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.jrsu.jharkhand.raksha.shakti.university.Objects.AppFont
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.R
import com.jrsu.jharkhand.raksha.shakti.university.Screen
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.Fire
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.NoticeViewModal
import com.jrsu.jharkhand.raksha.shakti.university.widget.NoticeItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController : NavController){

    val notice = viewModel(NoticeViewModal::class.java)
    val context = LocalContext.current
    var name by rememberSaveable{ mutableStateOf("")}

    Colors.SetStatusColor(Colors.AppWhite)

    LaunchedEffect(true){
        Fire.firebaseStore.collection("Users")
            .document(Fire.firebaseAuth!!.uid)
            .get()
            .addOnSuccessListener {
                name = it["name"].toString()
            }
    }

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 10.dp,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = Color.White,
        sheetContent = {
            Column(Modifier.fillMaxWidth()){

                ListItem(Modifier.clickable {
                    scope.launch { sheetState.hide() }
                    navController.navigate(Screen.SyllabusScreen.route)
                    },
                    icon = { Icon(Icons.Default.Assignment, null, tint = Color.DarkGray)}
                ){ Text("Syllabus", fontSize = T.width(4.5f).sp, color = Color.DarkGray)}

                ListItem(Modifier
                    .clickable {
                        scope.launch { sheetState.hide() }
                        Fire.firebaseStore.collection("Calendar")
                            .document("Academic Calendar").get()
                            .addOnSuccessListener {
                                val url = it["url"].toString()
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.setDataAndType(Uri.parse(url), "application/pdf")
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                try {
                                    context.startActivity(intent)
                                    //context.startActivity(Intent.createChooser(intent, "Open File"))
                                } catch (e: ActivityNotFoundException){
                                    Toast.makeText(context, "App not fount to open PDF", Toast.LENGTH_SHORT).show()
                                }
                            }
                    },
                    icon = { Icon(Icons.Default.CalendarToday, null, tint = Color.DarkGray)}
                ){ Text("Academic Calender", fontSize = T.width(4.5f).sp, color = Color.DarkGray) }

                ListItem(Modifier.clickable {
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://jrsuranchi.com/Default.aspx")
                )) },
                    icon = { Icon(Icons.Default.AccountBalance, null, tint = Color.DarkGray)}
                ){ Text("JRSU Website", fontSize = T.width(4.5f).sp, color = Color.DarkGray)}

                ListItem(Modifier.clickable {
                    FirebaseAuth.getInstance().signOut()
                    Fire.firebaseAuth = null
                    navController.navigate(Screen.LogInScreen.route) {
                        popUpTo(Screen.HomeScreen.route){ inclusive = true }
                    }},
                    icon = { Icon(Icons.Default.Logout, null, tint = Color.DarkGray)}
                ){Text("Log out", fontSize = T.width(4.5f).sp, color = Color.DarkGray)}
            }
        },
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Colors.AppWhite, Color.LightGray
                            ),
                            startY = 0.2f
                        )
                    ),
                Arrangement.Top,
                Alignment.CenterHorizontally
            ){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(T.width(5.5f).dp),
                    Arrangement.SpaceBetween,
                    Alignment.CenterVertically
                ){
                    Column{
                        Text(
                            "Hello",
                            fontSize = T.width(6f).sp ,
                            fontWeight = FontWeight.W600,
                            fontFamily = AppFont.mPlus,
                            color = Colors.AppOrange
                        )
                        Text(
                            text = name,
                            fontSize = T.width(5f).sp ,
                            fontWeight = FontWeight.W500,
                            fontFamily = AppFont.minigap
                        )
                    }
                    IconButton(onClick = { scope.launch {
                        sheetState.show()
                    } }){
                        Icon(
                            Icons.Rounded.Menu,
                            "",
                            Modifier.size(T.width(8f).dp),
                            Colors.AppOrange
                        )
                    }
                }
                val lazyState = rememberLazyListState()
                LazyColumn(
                    state = lazyState,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item{
                        Card(
                            Modifier
                                .fillMaxWidth(0.9f)
                                .height(T.height(28f).dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = 10.dp
                        ) {
                            Image(
                                rememberImagePainter(R.drawable.college_image),
                                "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    item{Spacer(Modifier.height(T.height(2f).dp))}
                    stickyHeader {
                        Card(
                            elevation = 2.5.dp,
                            shape = RoundedCornerShape(7.dp),
                        ){
                            Text(
                                text = "Notices",
                                fontSize = T.width(4.5f).sp ,
                                fontWeight = FontWeight.W600,
                                fontFamily = AppFont.mPlus,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 50.dp)
                            )
                        }
                    }
                    item{Spacer(Modifier.height(T.height(2f).dp))}
                    if(notice.list.isEmpty())
                        item{
                            CircularProgressIndicator(
                                Modifier.padding(20.dp),
                                color = Colors.AppOrange
                            )
                        }
                    else
                        itemsIndexed(notice.list){ _, item->
                            NoticeItem(
                                notice = item
                            )
                        }
                    item{Spacer(Modifier.height(T.height(1.5f).dp))}
                }
            }
        }
    }
}
