import com.google.gson.annotations.SerializedName

data class imagenDescription(
    @SerializedName("title") val title: String?,
    @SerializedName("imgpath") val imgpath: String?

)