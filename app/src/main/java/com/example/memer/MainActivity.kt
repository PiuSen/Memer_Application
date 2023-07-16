package com.example.memer


import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        LoadMeme()
    }
    private fun LoadMeme(){
        val imageView:ImageView=findViewById(R.id.imageView)
        val progress:ProgressBar=findViewById(R.id.progress)
        progress.visibility=View.VISIBLE

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                val url=response.getString("url")
                Glide.with(this).load(url).listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress.visibility=View.GONE
                        return false

                    }
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress.visibility=View.GONE
                        return false
                    }

                }).into(imageView)
                              },
            {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            })


        queue.add(JsonObjectRequest)
    }

    fun OnShare(view: View) {

        val inext=Intent(Intent.ACTION_SEND)
        inext.type="text/plain"
        inext.putExtra(Intent.EXTRA_TEXT,"JHNJKHJKUUJ")
        val chooser=Intent.createChooser(inext,"share............................")
        startActivity(inext)

    }
    fun OnNext(view: View) {
        LoadMeme()

    }
}