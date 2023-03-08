package com.jrsu.jharkhand.raksha.shakti.university.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.SyllabusViewModel
import com.jrsu.jharkhand.raksha.shakti.university.widget.SyllabusItem

@ExperimentalComposeUiApi
@Composable
fun SyllabusScreen(navController: NavController){

    val syllabus = viewModel(SyllabusViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(
            navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIosNew, null)
            } },
            contentColor = Colors.AppOrange,
            backgroundColor = Colors.AppWhite,
            title = {Text("Syllabus", fontWeight = FontWeight.W600)}
        )},
        backgroundColor = Colors.AppWhite,
    ) {
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item{Spacer(Modifier.height(T.height(2f).dp))}

            if(syllabus.list.isEmpty())
                item{
                    CircularProgressIndicator(
                        Modifier.padding(20.dp),
                        color = Colors.AppOrange
                    )
                }
            else
                itemsIndexed(syllabus.list){ _, item->
                    SyllabusItem(syllabus = item)
                }
            item{Spacer(Modifier.height(T.height(1.5f).dp))}
        }
    }

}
