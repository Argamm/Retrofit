package com.example.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.UsersAdapter
import com.example.retrofit.api.ApiService
import com.example.retrofit.dataClass.GetDatas
import com.example.retrofit.dataClass.GetImageViewModel
import com.example.retrofit.databinding.FragmentGetImageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GetImageFragment : Fragment() {
    lateinit var myUsersAdapter: UsersAdapter
    lateinit var binding: FragmentGetImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInfoRetrofit()

        binding.btnChange.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, SecondFragment())?.commit()
        }
    }

    private fun getInfoRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServer = retrofit.create(ApiService::class.java)
        val call: Call<GetDatas> = apiServer.getInfo()

        call.enqueue(object : Callback<GetDatas> {
            override fun onResponse(
                call: Call<GetDatas?>,
                response: Response<GetDatas?>
            ) {
                with(binding) {
                    if (response.isSuccessful) {
                        progressBLoading.visibility = View.GONE
                        btnChange.visibility = View.VISIBLE
                    }

                    context?.let { c ->
                        response.body()?.let {
                            myUsersAdapter = UsersAdapter(c, it.data)
                        }
                    }
                    recyclerView.adapter = myUsersAdapter
                    myUsersAdapter.notifyDataSetChanged()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<GetDatas?>, t: Throwable) {
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}