package com.sutonglabs.tracestore.ui.profile_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutonglabs.tracestore.data.decodeJwt
import com.sutonglabs.tracestore.data.getJwtToken
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileScreen(context: Context, onBackBtnClick: () -> Unit) {
    // Retrieve the JWT token as a flow
    val jwtTokenFlow: Flow<String?> = remember { getJwtToken(context) }
    val jwtToken = jwtTokenFlow.collectAsState(initial = null).value

    // Decode the JWT token to extract user info
    val userInfo = jwtToken?.let { decodeJwt(it) }

    userInfo?.let {
        val username = it.optString("username") ?: "Unknown User"
        val email = it.optString("email") ?: "Email not available"
        Log.d("ProfileScreen", "Username: $username")
        Log.d("ProfileScreen", "Email: $email")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = userInfo?.optString("username") ?: "Unknown User",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = userInfo?.optString("email") ?: "Email not available",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBackBtnClick) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        context = androidx.compose.ui.platform.LocalContext.current,
        onBackBtnClick = {}
    )
}
