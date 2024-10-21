package com.sutonglabs.tracestore.graphs.auth_graph

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sutonglabs.tracestore.graphs.Graph
import com.sutonglabs.tracestore.ui.home_screen.HomeScreen
import com.sutonglabs.tracestore.ui.login.LoginScreen
import com.sutonglabs.tracestore.ui.onboarding_screen.SplashScreen
import com.sutonglabs.tracestore.ui.signup.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.OnBoardingScreen.route
    ) {
        composable(route = AuthScreen.OnBoardingScreen.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (context as Activity).window.decorView.windowInsetsController?.hide(
                    WindowInsets.Type.statusBars()
                );
            } else {
                (context as Activity).window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
            SplashScreen(navController) //
            Log.d("Navigation Call", "Called Splash Screen")
        }
        composable(AuthScreen.SignInScreen.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (context as Activity).window.decorView.windowInsetsController?.show(
                    WindowInsets.Type.statusBars()
                );
            } else {
                (context as Activity).window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                }
            }
            LoginScreen(navController = navController)
        }
        composable(AuthScreen.SignUpScreen.route) {
            RegisterScreen(navController =navController )
        }
//        composable(AuthScreen.ForgetPasswordScreen.route) {
//            ForgetPasswordScreen(navController = navController)
//        }
//        composable(AuthScreen.OTPScreen.route) {
//            OTPScreen(navController = navController)
//        }
//        composable(AuthScreen.SignUpScreen.route) {
//            SignUpScreen(navController = navController)
//        }
        composable(AuthScreen.SignInSuccess.route) {
            HomeScreen()
        }

    }
}