package com.jrsu.jharkhand.raksha.shakti.university.viewmodel


import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class Fire @Inject constructor() : ViewModel(){

    companion object{
        var firebaseAuth = FirebaseAuth.getInstance().currentUser
        @SuppressLint("StaticFieldLeak")
        val firebaseStore = FirebaseFirestore.getInstance()
        val firebaseStorage = FirebaseStorage.getInstance()
    }

}

