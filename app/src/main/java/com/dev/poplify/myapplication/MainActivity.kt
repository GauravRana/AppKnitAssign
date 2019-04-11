package com.dev.poplify.myapplication

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import com.dev.poplify.myapplication.adapters.UserAdapter
import com.dev.poplify.myapplication.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.dev.poplify.myapplication.utils.PaginationScrollListener

class MainActivity : AppCompatActivity(), Observer {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private var currentPage = 0
    private val TOTAL_PAGES = 3


    lateinit var linearLayoutManager : LinearLayoutManager

    private val viewModel = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initbinding()
    }

    override fun update(p0: Observable?, p1: Any?) {

    }


    /***
     * Data binding
     */

    private fun initbinding(){
        val activityMainBinding =  DataBindingUtil.setContentView<com.dev.poplify.myapplication.databinding.ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.viewModel = viewModel
        addElements()
        linearLayoutManager =  LinearLayoutManager(this)
        rv_details.layoutManager = linearLayoutManager
        var adapter = UserAdapter(viewModel.user.userDetails, this)
        rv_details.adapter = adapter


        /***
         * scroll listener
         */
        rv_details?.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                main_progress.visibility = ProgressBar.VISIBLE
                isLoading = true
                currentPage += 1;

                if (currentPage == TOTAL_PAGES - 1){
                    isLastPage = true
                }

                Handler().postDelayed(Runnable {
                    main_progress.visibility = ProgressBar.GONE
                    addElements()
                    adapter.notifyDataSetChanged()
                }, 2000)

            }

        })
    }


    /***
     * Adding data on view
     */

    fun addElements(){
        isLoading = false
        viewModel.addObserver(this)
        val smentha = "<b>Smentha</b> Starts <b>Following You</b>"
        val Jenny = "<b>Jenny</b> Added a new Post"
        val Jen = "<b>Jenny</b> Commented on your Post"
        val Gary = "<b>Gary</b> Posted an image"
        val Mark = "<b>Mark</b> Posted an image"

        viewModel.addDetail(smentha, "15 Min ago")
        viewModel.addDetail(Jenny, "25 Min ago")
        viewModel.addDetail(Jen, "Yesterday")
        viewModel.addDetail(Gary, "1:40 PM")
        viewModel.addDetail(Mark, "1:00 PM")

        viewModel.addDetail(smentha, "15 Min ago")
        viewModel.addDetail(Jenny, "25 Min ago")
        viewModel.addDetail(Jen, "Yesterday")
        viewModel.addDetail(Gary, "1:40 PM")
        viewModel.addDetail(Mark, "1:00 PM")

    }
}