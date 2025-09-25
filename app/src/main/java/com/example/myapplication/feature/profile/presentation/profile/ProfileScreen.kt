package com.example.myapplication.feature.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.myapplication.feature.github.presentation.github.GithubViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(modifier: Modifier,
                  vm: ProfileViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(val st=state){
            is ProfileViewModel.ProfileStateUI.Init ->{
                Row {
                    Text(st.userProfile.userName.userName)
                    AsyncImage(
                        model = st.userProfile.pathPicture,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(st.userProfile.email.value)
            }

            is ProfileViewModel.ProfileStateUI.Error -> {
                Text(st.message)
            }
            ProfileViewModel.ProfileStateUI.Loading -> {
                Text("Loading...")
            }
        }
    }
}