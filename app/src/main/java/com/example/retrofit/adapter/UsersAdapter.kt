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

class UsersAdapter(
    private val context: Context,
    private var courseList: MutableList<ModelInfo>,
) : RecyclerView.Adapter<UsersAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.users_model,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.tvFirstName.text = courseList[position].first_name
        holder.tvSurname.text = courseList[position].last_name
        holder.tvEmail.text = courseList[position].email
        holder.tvId.text = courseList[position].id

        Glide
            .with(context)
            .load(courseList[position].avatar)
            .into(holder.courseIV)
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstName: TextView = itemView.findViewById(R.id.tvFirstName)
        val tvSurname: TextView = itemView.findViewById(R.id.tvSurname)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val courseIV: ImageView = itemView.findViewById(R.id.imgRandom)
    }
}
