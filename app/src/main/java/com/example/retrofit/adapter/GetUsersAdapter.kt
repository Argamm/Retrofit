package com.example.retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.dataClass.ModelInfo
import com.example.retrofit.dataClass.UsersModel

class GetUsersAdapter(
    private var courseList: MutableList<UsersModel>,
) : RecyclerView.Adapter<GetUsersAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.model_users_get,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.name.text = courseList[position].name
        holder.id.text = courseList[position].id
        holder.email.text = courseList[position].email
        holder.gender.text = courseList[position].gender
        holder.status.text = courseList[position].status
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvFirstNameSecond)
        val id: TextView = itemView.findViewById(R.id.tvIdSecond)
        val email: TextView = itemView.findViewById(R.id.tvEmailSecond)
        val gender: TextView = itemView.findViewById(R.id.tvGenderSecond)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
    }
}
