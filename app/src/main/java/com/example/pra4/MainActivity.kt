package com.example.pra4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Tema de la app
import com.example.pra4.ui.theme.Pra4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la app use mejor el espacio de la pantalla
        enableEdgeToEdge()

        // Aquí se carga la interfaz de Jetpack Compose
        setContent {
            Pra4Theme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Llamamos a la pantalla principal de la práctica
                    PantallaSeleccion(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaSeleccion(modifier: Modifier = Modifier) {

    // Controla si el menú está abierto o cerrado
    var expandido by remember { mutableStateOf(false) }

    // Guarda la opción que selecciona el usuario
    var opcionSeleccionada by remember {
        mutableStateOf("Selecciona una opción")
    }

    // Lista de opciones que aparecerán en el menú
    val opciones = listOf("Opción A", "Opción B", "Opción C")


    // Column acomoda los elementos de forma vertical
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título de la pantalla
        Text(
            text = "Lista Desplegable",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Contenedor principal del menú desplegable
        ExposedDropdownMenuBox(
            expanded = expandido,

            // Cambia el estado del menú: abierto o cerrado
            onExpandedChange = {
                expandido = !expandido
            }
        ) {

            // Campo de texto que muestra la opción seleccionada
            OutlinedTextField(
                value = opcionSeleccionada,

                // No se escribe directamente en el campo
                onValueChange = {},

                // Solo permite seleccionar, no escribir
                readOnly = true,

                label = {
                    Text("Elige una opción")
                },

                // Muestra la flecha del menú desplegable
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandido
                    )
                },

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            // Menú que se despliega con las opciones
            ExposedDropdownMenu(
                expanded = expandido,

                // Cierra el menú si se toca fuera de él
                onDismissRequest = {
                    expandido = false
                }
            ) {

                // Recorre la lista de opciones
                opciones.forEach { opcion ->

                    // Crea una opción dentro del menú
                    DropdownMenuItem(
                        text = {
                            Text(opcion)
                        },

                        onClick = {
                            // Guarda la opción seleccionada
                            opcionSeleccionada = opcion

                            // Cierra el menú después de seleccionar
                            expandido = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Muestra visualmente la opción elegida por el usuario
        Text(
            text = "Seleccionaste: $opcionSeleccionada",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Permite ver la práctica en Android Studio sin ejecutar en celular
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSeleccion() {
    Pra4Theme {
        PantallaSeleccion()
    }
}