package com.example.phoneapp.Ui_Layer.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.phoneapp.R
import com.example.phoneapp.Ui_Layer.ContactState
import com.example.phoneapp.Ui_Layer.ViewModel.ContactViewModel
import com.example.phoneapp.ui.theme.AppColor

@Composable
fun AddEditScreen(
    state: ContactState,
    navController: NavController,
    viewModel: ContactViewModel,
    onCloseBottomSheet: () -> Unit,
    onEvent: () -> Unit
) {
    var selectedImgUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImgUri = uri
            state.img.value=selectedImgUri.toString()
        }
    )

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Contact",
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            ), fontSize = 25.sp, color = Color.Blue
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = CircleShape,
            onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier.size(70.dp),
            elevation = CardDefaults.elevatedCardElevation(5.dp)
        ) {
            AsyncImage(
                model = selectedImgUri ?: R.drawable.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.image),

                )
        }

        Column() {
            Text(
                text = "Name",
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ), fontSize = 15.sp, color = Color.Black
            )
            TextField(
                placeholder = {
                    Text(
                        text = "John Doe",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_light)
                        ), fontSize = 15.sp, color = Color.LightGray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 15.sp
                ),
                value = state.name.value,
                onValueChange = { state.name.value = it },
                colors = TextFieldDefaults.colors(
                    focusedLeadingIconColor = AppColor,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedLabelColor = AppColor,
                    unfocusedLabelColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = AppColor,
                    unfocusedIndicatorColor = Color.LightGray,
                    unfocusedPlaceholderColor = AppColor,
                    errorTextColor = Color.Red
                )
            )
        }


        Spacer(modifier = Modifier.height(20.dp))
        Column() {
            Text(
                text = "Phone Number",
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ), fontSize = 15.sp, color = Color.Black
            )
            TextField(
                placeholder = {
                    Text(
                        text = "+91 01 2345 6789",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_light)
                        ), fontSize = 15.sp, color = Color.LightGray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "",
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 15.sp
                ),
                value = state.number.value,
                onValueChange = {
                    if (it.length <= 10) {
                        state.number.value = it
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedLeadingIconColor = AppColor,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedLabelColor = AppColor,
                    unfocusedLabelColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = AppColor,
                    unfocusedIndicatorColor = Color.LightGray,
                    unfocusedPlaceholderColor = AppColor,
                    errorTextColor = Color.Red
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Column() {
            Row {
                Text(
                    text = "Email",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_medium)
                    ), fontSize = 15.sp, color = Color.Black
                )
                Text(
                    text = " (Optional)",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_light)
                    ), fontSize = 15.sp, color = Color.Gray
                )

            }

            TextField(
                placeholder = {
                    Text(
                        text = "JohnDoe@gmail.com",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_light)
                        ), fontSize = 15.sp, color = Color.LightGray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "",
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 15.sp
                ),
                value = state.email.value.toString(),
                onValueChange = { state.email.value = it },
                colors = TextFieldDefaults.colors(
                    focusedLeadingIconColor = AppColor,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedLabelColor = AppColor,
                    unfocusedLabelColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = AppColor,
                    unfocusedIndicatorColor = Color.LightGray,
                    unfocusedPlaceholderColor = AppColor,
                    errorTextColor = Color.Red
                )
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
        ElevatedButton(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            onClick = {
                if (state.name.value.isEmpty() || state.number.value.isEmpty()) {
                    Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
                } else {
                    onCloseBottomSheet.invoke()
                    onEvent.invoke()
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(
                text = "Add Contact",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ), fontSize = 17.sp, color = Color.White
            )
        }
    }
}