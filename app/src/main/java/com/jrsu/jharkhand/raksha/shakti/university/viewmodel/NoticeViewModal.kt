package com.jrsu.jharkhand.raksha.shakti.university.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Notice
import javax.inject.Inject

class NoticeViewModal @Inject constructor() : ViewModel() {

    var list by mutableStateOf(listOf<Notice>())
    init{ getData() }

    private fun getData(){
        Fire.firebaseStore.collection("Notice")
            .addSnapshotListener { value, _ ->
                list = listOf()
                value?.documents?.forEach {
                    list = listOf(
                        Notice(
                            it["key"].toString(),
                            it["title"].toString(),
                            it["uploaderName"].toString(),
                            it["type"].toString(),
                            it["url"].toString(),
                            it["time"].toString()
                    )) + list
                }
            }
    }
}