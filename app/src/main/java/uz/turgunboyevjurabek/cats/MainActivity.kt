package uz.turgunboyevjurabek.cats

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem
import uz.turgunboyevjurabek.cats.ui.theme.CatsTheme
import uz.turgunboyevjurabek.cats.utils.Status

@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var myViewModel:MyViewModel
    @SuppressLint("MutableCollectionMutableState")
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
                            myViewModel= ViewModelProvider(this@MainActivity)[MyViewModel::class.java]
                            myViewModel.getProduct().observe(this@MainActivity, Observer {
                                when(it.status){
                                    Status.LOADING -> {
                                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                                    }
                                    Status.ERROR -> {
                                        Toast.makeText(context, ":( ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                                    Status.SUCCESS -> {
                                        data = ArrayList(it.data!!)
                                        Toast.makeText(context, "Joningdann", Toast.LENGTH_SHORT).show()
                                        Log.d("URAA",data.toString())
                                    }
                                }
                            })

                    }
                    List(list = data)
                }
            }
        }
    }
    @Composable
    private fun List(list:ArrayList<GetAllProductItem>) {

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(Modifier.fillMaxSize()){
                items(list.size){
                    UI(getAllProductItem = list[it])
                }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun UI(getAllProductItem: GetAllProductItem) {
       OutlinedCard(modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = 20.dp, vertical = 10.dp)){
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {
                    var size=getAllProductItem.image?.length?.toString()!!.toInt()-1
                    val img= getAllProductItem.image.substring(4..size)
                    val successImage="https$img"
                    var imageUrl by remember { mutableStateOf(successImage) }


                    Image(painter = rememberImagePainter(data = imageUrl,
                        builder = {
                                  transformations(CircleCropTransformation())
                            crossfade(2000)
                        }
                    ), contentDescription =null,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(10.dp)
                        
                        )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = getAllProductItem.name.toString(), fontSize = 20.sp)
                }
            }
    }
}


