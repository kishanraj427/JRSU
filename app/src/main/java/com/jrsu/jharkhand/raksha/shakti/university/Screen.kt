package com.jrsu.jharkhand.raksha.shakti.university

sealed class Screen(val route: String){
    object SplashScreen : Screen("Splash Screen")
    object LogInScreen : Screen("LogIn Screen")
    object SingUpScreen : Screen("SignUp Screen")
    object HomeScreen : Screen("Home Screen")
    object SyllabusScreen : Screen("Syllabus Screen")

}
