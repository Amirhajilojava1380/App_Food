package com.example.newfood.bindingadapter

import android.util.Log
import android.view.RoundedCorner
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.newfood.R
import com.google.android.material.shape.RoundedCornerTreatment
import com.squareup.picasso.Picasso
import java.lang.Exception


class RecipesRowBinding {


    companion object{


        @BindingAdapter("loadImageUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView ,imageUrl: String){
            val fadeInAnimation = AnimationUtils.loadAnimation(imageView.context,R.anim.setpicasso)
            Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error)
                .into(imageView, object :com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        imageView.startAnimation(fadeInAnimation)
                    }

                    override fun onError(e: Exception?) {
                        if (e != null) {
                            Log.d("loadImageUrl" , "ImageException:"+e.message)
                        }
                    }

                })




        }


        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView  , likes :Int){
            textView.text = likes.toString()
        }



        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView  , minutes :Int){
            textView.text = minutes.toString()
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan:Boolean){
            if (vegan){

                when(view){

                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context , R.color.green))
                    }

                    is ImageView ->{
                        view.setColorFilter(ContextCompat.getColor(view.context , R.color.green))
                    }

                }

            }
        }




    }


}