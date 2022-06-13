package com.smartphonecodes.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.smartphonecodes.marsphotos.network.MarsPhoto
import com.smartphonecodes.marsphotos.overview.MarsApiStaus
import com.smartphonecodes.marsphotos.overview.PhotoGridAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView:RecyclerView , data:List<MarsPhoto>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView , status : MarsApiStaus?){
    when(status){

        MarsApiStaus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }


        MarsApiStaus.DONE -> {
            statusImageView.visibility = View.GONE
        }


        MarsApiStaus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
