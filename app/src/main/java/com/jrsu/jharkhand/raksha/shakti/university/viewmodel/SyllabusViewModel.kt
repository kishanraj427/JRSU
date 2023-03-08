package com.jrsu.jharkhand.raksha.shakti.university.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Syllabus
import javax.inject.Inject

class SyllabusViewModel @Inject constructor() : ViewModel() {

    var list by mutableStateOf(listOf<Syllabus>())
    init{ getData() }

    private fun getData(){
        Fire.firebaseStore.collection("Syllabus")
            .addSnapshotListener { value, _ ->
                list = listOf()
                value?.documents?.forEach {
                    list = listOf(
                        Syllabus(
                            it["key"].toString(),
                            it["title"].toString(),
                            it["url"].toString()
                        )
                    ) + list
                }
            }
    }
}