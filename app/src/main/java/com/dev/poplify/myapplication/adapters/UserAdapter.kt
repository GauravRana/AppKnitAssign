package com.dev.poplify.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.poplify.myapplication.R
import com.dev.poplify.myapplication.data.UserDetail
import kotlinx.android.synthetic.main.detail_list_item.view.*

class UserAdapter(var userDetail: MutableList<UserDetail> = mutableListOf(), val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int {
        return userDetail.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.detail_list_item,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder?.tvUserName?.text = Html.fromHtml(userDetail.get(position).name.toString())
        holder?.tvTime?.text = userDetail.get(position).info.toString()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvUserName = view.tvUserName
    val tvTime = view.tvTime
}
