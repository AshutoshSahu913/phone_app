package com.example.phoneapp.Ui_Layer.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.phoneapp.R
import com.example.phoneapp.Ui_Layer.Navigation.Routes
import com.example.phoneapp.Ui_Layer.ViewModel.ContactViewModel
import com.example.phoneapp.ui.theme.AppColor

@Composable
fun ProfileScreen(navHostController: NavHostController, viewModel: ContactViewModel) {

    val context = LocalContext.current
    var selectedImgUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val name = remember {
        mutableStateOf("")
    }

    val phone = remember {
        mutableStateOf("")
    }

    val mail = remember {
        mutableStateOf("")
    }

    val errorName = remember {
        mutableStateOf(false)
    }

    val errorPhone = remember {
        mutableStateOf(false)
    }
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImgUri = uri }
        )
    val scrollState = rememberScrollState()
    Scaffold(content = {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(it)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                painter = painterResource(id = R.drawable.profileimg),
                contentDescription = ""
            )
            Text(
                modifier = Modifier,
                text = "Enter Your Personal Details",
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily(
                    Font(R.font.poppins_light)
                ), fontSize = 20.sp, color = Color.Black
            )

            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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


                Spacer(modifier = Modifier.height(20.dp))
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
                        isError = errorName.value,
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
                        value = name.value,
                        onValueChange = { name.value = it },
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
                            errorTextColor = Color.Red,
                            errorContainerColor = Color.White,
                            errorPlaceholderColor = Color.Red,
                            errorLeadingIconColor = Color.Red,
                            errorIndicatorColor = Color.Red,
                            errorLabelColor = Color.White,


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
                        isError = errorPhone.value,
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
                        value = phone.value,
                        onValueChange = { number ->
                            if (number.length <= 10) {
                                phone.value = number
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
                            errorTextColor = Color.Red,
                            errorContainerColor = Color.White,
                            errorPlaceholderColor = Color.Red,
                            errorLeadingIconColor = Color.Red,
                            errorIndicatorColor = Color.Red,
                            errorLabelColor = Color.White,
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
                        value = mail.value,
                        onValueChange = { mail.value = it },
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
                        errorName.value = false
                        errorPhone.value = false

                        if (name.value.isEmpty() || phone.value.isEmpty()) {
                            if (name.value.isEmpty()) {
                                errorName.value = true
                            }
                            if (phone.value.isEmpty()) {
                                errorPhone.value = true
                            }
                            Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                .show()

                        } else {

                            viewModel.saveId(1)
                            viewModel.saveImg(selectedImgUri.toString())
                            viewModel.saveUserName(name = name.value)
                            viewModel.savePhoneNumber(phone = phone.value)
                            viewModel.saveEmail(email = mail.value)
                            navHostController.navigate(Routes.HomeScreen.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(
                        text = "Done",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_medium)
                        ), fontSize = 17.sp, color = Color.White
                    )
                }
            }
        }
    })

}