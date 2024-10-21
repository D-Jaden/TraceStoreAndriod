package com.sutonglabs.tracestore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.sutonglabs.tracestore.api.TraceStoreAPI
import com.sutonglabs.tracestore.graphs.root_graph.RootNavigationGraph
import com.sutonglabs.tracestore.ui.theme.TraceStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: TraceStoreAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraceStoreTheme {
                ShowScreen(LocalContext.current)
            }
        }
    }
    @Composable
    private fun ShowScreen(context: Context) {
        val navHostController = rememberNavController()
//        val viewModelStoreOwner = remember { this as ViewModelStoreOwner }
//
//        LaunchedEffect(Unit) {
//            navHostController.setViewModelStore(viewModelStoreOwner.viewModelStore)
//        }
        RootNavigationGraph(navHostController = navHostController, context = context)
    }
}


    //    private  fun fetchProducts() {
//        val call = apiService.getProducts()
//
//        call.enqueue(object : Callback<ProductResponse> {
//            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
//                if (response.isSuccessful) {
//                    val products = response.body()?.data
//                    products?.forEach {
//                        Log.d("MainActivity", "Product: ${it.name}, Price: ${it.price}")
//                    }
//                } else {
//                    Log.e("MainActivity", "Response failed with status: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
//                Log.e("MainActivity", "Network request failed: ${t.message}")
//            }
//        })
//    }
