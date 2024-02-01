package uz.turgunboyevjurabek.cats

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem
import uz.turgunboyevjurabek.cats.madels.GetImageResponse
import uz.turgunboyevjurabek.cats.madels.GetImageResponseItem
import uz.turgunboyevjurabek.cats.network.ApiClient
import uz.turgunboyevjurabek.cats.ui.theme.CatsTheme
import uz.turgunboyevjurabek.cats.utils.Status

class MainActivity : ComponentActivity() {
    private lateinit var myViewModel:MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val scope= rememberCoroutineScope()
                    var data by remember { mutableStateOf(ArrayList<GetAllProductItem>()) }
                    val context= LocalContext.current

                    LaunchedEffect(key1 = true){
                            myViewModel= ViewModelProvider(this@MainActivity).get(MyViewModel::class.java)
                            myViewModel.getProduct().observe(this@MainActivity, Observer {
                                when(it.status){
                                    Status.LOADING -> {
                                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                                    }
                                    Status.ERROR -> {
                                        Toast.makeText(context, ":( ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                                    Status.SUCCESS -> {
                                        Toast.makeText(context, "Joningdann", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                            Log.d("URAA",data.toString())

                    }
                    if (data.isNotEmpty()){
                        Toast.makeText(context, "keldi", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
