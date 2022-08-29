package com.example.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.GetUsersAdapter
import com.example.retrofit.api.UsersService
import com.example.retrofit.dataClass.UsersModel
import com.example.retrofit.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    lateinit var myUsersAdapter: GetUsersAdapter
    lateinit var courseList: ArrayList<UsersModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseList = ArrayList()
        getInfoRetrofit()
    }

    private fun getInfoRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gorest.co.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServer = retrofit.create(UsersService::class.java)
        val call: Call<ArrayList<UsersModel>> = apiServer.getUser()

        call.enqueue(object : Callback<ArrayList<UsersModel>> {
            override fun onResponse(
                call: Call<ArrayList<UsersModel>?>,
                response: Response<ArrayList<UsersModel>?>
            ) {
                with(binding) {
                    if (response.isSuccessful) {
                        progressBLoadingSecond.visibility = View.GONE
                        courseList = response.body()!!
                    }
                    myUsersAdapter = GetUsersAdapter(courseList)
                    recyclerViewSecond.adapter = myUsersAdapter
                    myUsersAdapter.notifyDataSetChanged()
                    recyclerViewSecond.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<ArrayList<UsersModel>?>, t: Throwable) {
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}