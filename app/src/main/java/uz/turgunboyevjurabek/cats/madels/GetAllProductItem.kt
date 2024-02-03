package uz.turgunboyevjurabek.cats.madels

import com.google.gson.annotations.SerializedName

 class GetAllProductItem{
     @SerializedName("amount")
     val amount: Int?=null
     @SerializedName("category")
     val category: Int?=null
     @SerializedName("create_at")
     val createAt: String?=null
     @SerializedName("id")
     val id: Int?=null
     @SerializedName("image")
     val image: String?=null
     @SerializedName("last_price")
     val lastPrice: String?=null
     @SerializedName("name")
     val name: String?=null
     @SerializedName("optom_price")
     val optomPrice: String?=null
     @SerializedName("other_images")
     val otherImages: List<Any>?=null
     @SerializedName("sell_price")
     val sellPrice: String?=null
     @SerializedName("update_at")
     val updateAt: String?=null
     override fun toString(): String {
         return "GetAllProductItem(amount=$amount, category=$category, createAt=$createAt, id=$id, image=$image, lastPrice=$lastPrice, name=$name, optomPrice=$optomPrice, otherImages=$otherImages, sellPrice=$sellPrice, updateAt=$updateAt)"
     }


 }
