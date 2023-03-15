package com.example.networkingpractise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.networkingpractise.Adapter.UserAdapter
import com.example.networkingpractise.databinding.ActivityThirdBinding
import com.example.networkingpractise.models.UserItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class ThirdActivity : AppCompatActivity() {

    lateinit var binding: ActivityThirdBinding
    lateinit var requestQueue: RequestQueue

    var url = "https://jsonplaceholder.typicode.com/users"

    private val TAG = "ThirdActivity"

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, url, null, object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {


                    val type = object : TypeToken<List<UserItem>>() {}.type
                    var list: List<UserItem> = Gson().fromJson(response.toString(), type)

                    userAdapter = UserAdapter(list)

                    binding.rv.adapter = userAdapter

                    //log berish
                    Log.d(TAG, "onResponse: ${list}")

                }

            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {


                }

            })

        requestQueue.add(jsonArrayRequest)

        

    }
}