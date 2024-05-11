package com.example.bugit.features.home_screen.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bugit.R
import com.example.bugit.ui.theme.BugitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BugitTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BugSubmissionScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun BugSubmissionScreen() {
    val viewModel = viewModel<HomeScreenViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.onEvent(HomeScreenEvent.OnImageUriChanged(uri)) }
    )

    val intent = (LocalContext.current as Activity).intent
    LaunchedEffect(intent) {
        if (Intent.ACTION_SEND == intent.action && intent.type?.startsWith("image/") == true) {
            val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            if (imageUri != null) {
                viewModel.onEvent(HomeScreenEvent.OnImageUriChanged(imageUri))
            }
        }
    }
    LaunchedEffect(state.showErrorDialog) {
        if (state.showErrorDialog) {
            Toast.makeText(context, state.dialogText, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(HomeScreenEvent.DismissAllDialog)
        }
    }
    CircularProgressBar(state = state)
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.background(Color.Blue),
            title = {
                Text(stringResource(R.string.report_a_problem))
            },
            actions = {
                IconButton(onClick = { viewModel.onEvent(HomeScreenEvent.Submit) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_submit),
                        contentDescription = "submit"
                    )
                }
            },

            colors = topAppBarColors(
                containerColor = colorResource(id = R.color.blue),
            )


        )

        OutlinedTextField(
            value = state.description,
            onValueChange = {
                viewModel.onEvent(HomeScreenEvent.OnDescriptionChanged(it))
            },
            label = { Text(stringResource(R.string.what_went_wrong)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(0.5f)

        )

        AsyncImage(
            model = state.selectedImageUri,
            contentDescription = null, // Provide a content description if needed
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(size = 5.dp))
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .imePadding(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_screenshot),
                        contentDescription = "Button Icon",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.take_a_screenshot))
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_gallery),
                        contentDescription = "Button Icon",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.select_image_from_gallery))
                }
            }

        }

    }
}

@Composable
fun CircularProgressBar(modifier: Modifier = Modifier, state: HomeScreenState) {
    if (state.showLoadingDialog)
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Blue, // Customize the color of the progress indicator
                strokeWidth = 8.dp // Customize the stroke width of the progress indicator
            )
        }
}


