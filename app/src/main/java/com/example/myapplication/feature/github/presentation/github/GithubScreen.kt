package com.example.myapplication.feature.github.presentation.github

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun GithubScreen( modifier: Modifier,
                  vm: GithubViewModel = koinViewModel()
                ) {
    var nickName by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    //val context = LocalContext.current
    //var showImage = remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("")
        OutlinedTextField(
            value = nickName,
            onValueChange = {
                it -> nickName = it
            }
        )
//        OutlinedTextField(
//            state = rememberTextFieldState(),
//            lineLimits = TextFieldLineLimits.SingleLine,
//            label = { Text("Label") },
//        )
        OutlinedButton(
            onClick = {
                vm.fetchAlias(nickName)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }
        when(val st=state){
            is GithubViewModel.GitHubStateUI.Error ->{
                Text(st.message)
            }
            GithubViewModel.GitHubStateUI.Init -> {
                Text("Init")
            }
            GithubViewModel.GitHubStateUI.Loading -> {
                Text("Loading...")
            }
            is GithubViewModel.GitHubStateUI.Success -> {
                Text(st.github.nickName)
                AsyncImage(
                    model = st.github.pathURL,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
                Text(st.github.pathURL)
            }
        }
//        if (showImage.value) {
//            AsyncImage(
//                model = ImageRequest.Builder(context)
//                    .data("https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2017/07/rick-morty-.jpg?fit=1200%2C675&quality=70&strip=all&ssl=1")
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "Imagen desde URL",
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
    }
}
