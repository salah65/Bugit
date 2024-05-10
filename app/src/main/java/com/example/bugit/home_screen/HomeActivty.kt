package com.example.bugit.home_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var bugDescription by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.background(Color.Blue),
            title = {
                Text(stringResource(R.string.report_a_problem))
            },
            actions = {
                IconButton(onClick = { /* Handle button click */ }) {
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
            value = bugDescription,
            onValueChange = { bugDescription = it },
            label = { Text(stringResource(R.string.what_went_wrong)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(0.5f)

        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
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
                onClick = { /* Implement image picker */ }
            ) {
                Text(stringResource(R.string.take_a_screenshot))
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* Implement image picker */ }
            ) {
                Text(stringResource(R.string.select_image_from_gallery))
            }

        }

    }
}


