import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.st.auth.presentation.AuthViewAction
import com.st.auth.presentation.AuthViewModel
import com.st.auth.presentation.AuthViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.handleGoogleSignInIntent(result.data)
            } else {
                Log.e("AuthScreen", "Google Sign-In cancelado ou falhou: ${result.data?.extras}")
                viewModel.handleGoogleSignInIntent(null)
            }
        }
    )


    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(viewModel.viewAction) {
        viewModel.viewAction.collectLatest { action ->
            when (action) {
                is AuthViewAction.NavigateToHome -> navigateToHome()
                is AuthViewAction.LoginWithGoogle -> TODO()
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Login") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (viewState) {
                is AuthViewState.Idle -> LoginButton {
                    coroutineScope.launch {
                        val signInRequest = viewModel.startGoogleSignIn()
                        signInRequest?.let { launcher.launch(it) }
                    }
                }
                is AuthViewState.Loading -> CircularProgressIndicator()
                is AuthViewState.Success -> {
                    val successState = viewState as AuthViewState.Success
                    Text("Bem-vindo, ${successState.userName}!")
                }
                is AuthViewState.Error -> {
                    val errorState = viewState as AuthViewState.Error
                    Snackbar {
                        Text(errorState.message)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LoginButton {
                        coroutineScope.launch {
                            val signInRequest = viewModel.startGoogleSignIn()
                            signInRequest?.let { launcher.launch(it) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Entrar com Google")
    }
}
