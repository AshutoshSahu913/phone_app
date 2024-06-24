package com.example.phoneapp.Ui_Layer.Screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.phoneapp.Data.Database.Contact
import com.example.phoneapp.R
import com.example.phoneapp.Ui_Layer.ContactState
import com.example.phoneapp.Ui_Layer.ViewModel.ContactViewModel
import com.example.phoneapp.ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: ContactState,
    navController: NavHostController,
    viewModel: ContactViewModel,
    userName: String,
    userPhone: String,
    userEmail: String,
    userImg: String
) {
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(false)
    }

    val selectedContact = remember {
        mutableStateOf<Contact?>(null)
    }
    Scaffold(topBar = {
//        TopAppBar(
//            colors = TopAppBarDefaults.topAppBarColors(Color.Blue), title = {
//                Row(
//                    modifier = Modifier
//                        .heightIn(56.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "Contact App",
//                        fontStyle = FontStyle.Normal,
//                        fontFamily = FontFamily(
//                            Font(R.font.poppins_medium)
//                        ), fontSize = 20.sp, color = Color.White
//                    )
//
//                    Icon(
//                        imageVector = Icons.Default.Refresh,
//                        contentDescription = "",
//                        tint = Color.White, modifier = Modifier
//                            .padding(20.dp)
//                            .clickable {
//                                viewModel.changeSorting()
//                            }
//                    )
//                }
//            })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showBottomSheet.value = true },
            containerColor = Color.Blue,
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        )
    }, floatingActionButtonPosition = FabPosition.End, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),

                elevation = CardDefaults.elevatedCardElevation(1.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                )
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "Hello, ",
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 30.sp,
                                color = Color.Black,
                                fontStyle = FontStyle.Normal
                            )
                            Text(
                                text = userName,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 25.sp,
                                color = AppColor,
                                fontStyle = FontStyle.Normal
                            )

                        }
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "Contact No.  ",
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 10.sp,
                                color = Color.Black,
                                fontStyle = FontStyle.Normal
                            )
                            Text(
                                text = userPhone,
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 10.sp,
                                fontStyle = FontStyle.Normal
                            )
                        }

                        if (userEmail.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "Email Address  ",
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    fontStyle = FontStyle.Normal
                                )
                                Text(
                                    text = userEmail,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontSize = 10.sp,
                                    fontStyle = FontStyle.Normal
                                )
                            }
                        }


                    }
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .size(70.dp),
                        model = userImg.toUri(),
                        contentDescription = ""
                    )
                }

            }

            val selectSort = remember {
                mutableStateOf(false)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    text = "All Contacts",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_black)
                    ), fontSize = 25.sp, color = Color.Blue


                )
                Icon(
                    painter = painterResource(
                        id = if (selectSort.value)
                            R.drawable.sort_amount_up_svgrepo_com
                        else R.drawable.sort_amount_down_svgrepo_com
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(25.dp)
                        .clickable {
                            viewModel.changeSorting()
                            selectSort.value = !selectSort.value
                        }
                )

            }
            if (state.contact.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_img),
                        contentDescription = "",
                        modifier = Modifier.size(250.dp)
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "EMPTY CONTACT",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_regular)
                        ), fontSize = 25.sp, color = Color.Blue

                    )
                }
            } else {
                LazyColumn {
                    items(state.contact) { contacts ->
                        ContactCardItems(context = context, contacts = contacts) {
                            selectedContact.value = contacts
                            state.id.value = selectedContact.value!!.id
                            state.name.value = selectedContact.value!!.userName
                            state.number.value = selectedContact.value!!.phoneNumber
                            state.email.value = selectedContact.value!!.emailAddress
                            state.dataOfCreation.value = selectedContact.value!!.dataOfCreation
                            state.img.value = selectedContact.value!!.image
                            showDialog = true
                        }
                    }
                }

            }
            if (showDialog) {
                ContactDetailsEditCard(
                    state = state,
                    contact = selectedContact.value!!,
                    onDismiss = {
                        state.id.value = 0
                        state.name.value = ""
                        state.number.value = ""
                        state.email.value = ""
                        state.dataOfCreation.value = 0
                        state.img.value = ""
                        showDialog = false

                    },
                    onDelete = {
                        state.id.value = selectedContact.value!!.id
                        state.name.value = selectedContact.value!!.userName
                        state.number.value = selectedContact.value!!.phoneNumber
                        state.email.value = selectedContact.value!!.emailAddress
                        state.dataOfCreation.value = selectedContact.value!!.dataOfCreation
                        state.img.value = selectedContact.value!!.image
                        viewModel.deleteContacts()
                        showDialog = false
                    }

                ) {
                    state.name.value.ifEmpty { selectedContact.value!!.userName }
                    state.number.value.ifEmpty { selectedContact.value!!.phoneNumber }
                    state.email.value!!.ifEmpty { selectedContact.value!!.emailAddress }
                    state.img.value!!.ifEmpty { selectedContact.value!!.image }
                    viewModel.saveContact()
                    Toast.makeText(context, "Update ${state.name.value} Details", Toast.LENGTH_SHORT)
                        .show()
                    showDialog = false
                }
            }
        }
    },
        bottomBar = {
            if (showBottomSheet.value) {
                ModalBottomSheet(
                    modifier = Modifier.padding(bottom = 0.dp),
                    onDismissRequest = { showBottomSheet.value = false },
                    sheetState = sheetState,
                    containerColor = Color.White
                ) {
                    AddEditScreen(
                        state = state,
                        navController = navController,
                        viewModel = viewModel,
                        onCloseBottomSheet = { showBottomSheet.value = false },
                    ) {
                        viewModel.saveContact()
                    }
                }
            }
        })
}

@Composable
fun ContactDetailsEditCard(
    contact: Contact,
    onDismiss: () -> Unit,
    state: ContactState,
    onDelete: () -> Unit,
    onEvent: () -> Unit
) {
    val context = LocalContext.current
    var selectedImgUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImgUri = uri
            state.img.value = selectedImgUri.toString()
        }
    )

    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    onClick = {
                        onDelete.invoke()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "Delete",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_medium)
                        ), fontSize = 14.sp, color = Color.White
                    )
                }

                ElevatedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 5.dp, vertical = 5.dp),
                    onClick = {
                        if (state.name.value.isEmpty() || state.number.value.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please Fill All Fields",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            onEvent.invoke()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(
                        text = "Save ",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_medium)
                        ), fontSize = 14.sp, color = Color.White
                    )
                }
            }

        },
        containerColor = Color.White,
        titleContentColor = AppColor,
        textContentColor = Color.Black,
        text = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Edit Contact Here ⚠️",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_medium)
                    ), fontSize = 20.sp, color = Color.Blue
                )
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    shape = CircleShape,
                    elevation = CardDefaults.elevatedCardElevation(2.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )
                ) {
                    AsyncImage(
                        placeholder = painterResource(id = R.drawable.image),
                        model = selectedImgUri ?: state.img.value,
                        contentDescription = "",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop,
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
                        value = state.email.value!!,
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
            }
        }
    )
}

@Composable
fun ContactCardItems(context: Context, contacts: Contact, onCardClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(3.dp)
            .clickable {
                onCardClick.invoke()
            }

    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier.padding(5.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    Color.White
                )
            ) {
                AsyncImage(
                    placeholder = painterResource(id = R.drawable.image),
                    model = contacts.image,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f),
                    text = contacts.userName,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontStyle = FontStyle.Normal
                )
                Row(modifier = Modifier) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "",
                        tint = Color.Green,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(25.dp)
                            .clip(shape = CircleShape)
                            .background(
                                Color.White
                            )
                            .padding(4.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${contacts.phoneNumber}")
                                }
                                context.startActivity(intent)
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(25.dp)
                            .clip(shape = CircleShape)
                            .background(
                                Color.Blue
                            )
                            .padding(4.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${contacts.emailAddress}")
                                }
                                context.startActivity(intent)
                            }
                    )
                }
            }
        }
    }
}