package com.geraa1985.mykotlinmvpapp.mvp.model.okhttp

import android.util.Log
import okhttp3.*
import java.io.IOException

class OkHttpRequest {

    //Инициализируем клиент
    private val client = OkHttpClient.Builder().build()

    //Инициализируем запрос
    private val request = Request.Builder()
        .url("https://api.github.com/users")
        .build()

    //Планируем запрос
    fun request() {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Выполняется при ошибке
                Log.e("",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                //Выполняется при успехе
                //Получаем тело ответа и выводим в лог в виде строки
                response.body?.let {
                    Log.d("",it.string())
                }
            }
        })
    }
}

