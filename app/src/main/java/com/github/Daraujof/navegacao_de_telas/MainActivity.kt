package com.github.Daraujof.navegacao_de_telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.github.Daraujof.navegacao_de_telas.Screens.LoginScreen
import com.github.Daraujof.navegacao_de_telas.Screens.MenuScreen
import com.github.Daraujof.navegacao_de_telas.Screens.PedidosScreen
import com.github.Daraujof.navegacao_de_telas.Screens.PerfilScreen
import com.github.Daraujof.navegacao_de_telas.ui.theme.Navegacao_de_telasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navegacao_de_telasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "pedidos") {
                            PedidosScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "perfil/{nome}") {
                            val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
                            PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!)
                        }
                    }
                }
            }
        }
    }
}