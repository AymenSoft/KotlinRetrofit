package com.app.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.retrofit.models.Post
import com.app.retrofit.models.User
import com.app.retrofit.retrofit.RetrofitInstance
import kotlinx.coroutines.*


@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPost()

    }

    private fun getUsers(){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = RetrofitInstance().apiService.getUsers()
                if (response.isSuccessful && response.body() != null){
                    val content = response.body()
                    val usersList = ArrayList<User>(content)
                    Log.e("size", content!!.size.toString())
                    Log.e("users", usersList.toString())
                }
            }catch (e: Exception){
                Log.e("exception", e.message.toString())
            }
        }
    }

    private fun getPostById(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitInstance().apiService.getPostById(1)
                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    Log.e("content", content!!.title.toString())
                    //do something
                } else {
                    Log.e("error", response.message())
                }
            } catch (e: Exception) {
                Log.e("exception", e.message.toString())
            }
        }
    }

    private fun getCommentsByPost(){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = RetrofitInstance().apiService.getCommentsByPost(1)
                if (response.isSuccessful && response.body() != null){
                    val content = response.body()
                    Log.e("size", content!!.size.toString())
                    Log.e("content", content.toString())
                }
            }catch (e: Exception){
                Log.e("exception", e.message.toString())
            }
        }
    }

    private fun createPost(){
        val post = Post(1, 1, "test", "test")
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = RetrofitInstance().apiService.createPost(post)
                if (response.isSuccessful && response.body() != null){
                    val content = response.body()
                    Log.e("content", content.toString())
                }
            }catch (e: Exception){
                Log.e("exception", e.message.toString())
            }
        }
    }


}