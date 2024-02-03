package uz.turgunboyevjurabek.cats

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import uz.turgunboyevjurabek.cats.madels.GetAllProductItem
import uz.turgunboyevjurabek.cats.madels.GetImageResponse
import uz.turgunboyevjurabek.cats.madels.GetImageResponseItem
import uz.turgunboyevjurabek.cats.network.ApiClient
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
            LazyColumn(){
                items(list.size){
                    UI(getAllProductItem = list[it])
                }
            }
        }

    }

    @Composable
    fun UI(getAllProductItem: GetAllProductItem) {
       Box(modifier = Modifier
           .fillMaxWidth()
           .background(Color.Cyan)
           .padding(horizontal = 20.dp, vertical = 10.dp)){
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {

//                        val image=getAllProductItem.image?.substring(3..getAllProductItem.image.length)
//                    Toast.makeText(LocalContext.current, "${getAllProductItem.image?.length}", Toast.LENGTH_SHORT).show()
//                        val goldImage= "https$image"
                    var imageUrl by remember { mutableStateOf(getAllProductItem.image) }

                    Image(painter = rememberImagePainter(data = imageUrl),
                            contentDescription =null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )

                    Text(text = getAllProductItem.name.toString(), fontSize = 20.sp)
                }
            }
    }
}


