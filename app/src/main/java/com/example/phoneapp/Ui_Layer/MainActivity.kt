package com.example.phoneapp.Ui_Layer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.phoneapp.Ui_Layer.Navigation.NavGraph
import com.example.phoneapp.Ui_Layer.ViewModel.ContactViewModel
import com.example.phoneapp.ui.theme.PhoneAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

//            val dataStore: DataStore<Preferences> by lazy {
//                PreferenceDataStoreFactory.create(
//                    corruptionHandler = ReplaceFileCorruptionHandler(
//                        produceNewData = { emptyPreferences() }
//                    ),
//                    produceFile = { preferencesDataStoreFile("user_preferences") }
//                )
//            }
            val viewModel = hiltViewModel<ContactViewModel>()
            val state by viewModel.state.collectAsState()

            val navController = rememberNavController()
            PhoneAppTheme {
                NavGraph(
                    navHostController = navController,
                    viewModel = viewModel,
                    state = state,
//                    dataStore = dataStore
                )

            }
        }
    }
}

