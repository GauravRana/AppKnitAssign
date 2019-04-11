package com.dev.poplify.myapplication.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        var totalItemCount = 0
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        totalItemCount = layoutManager.itemCount

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                Log.d("RecordsFetched" , ""+totalItemCount)
                if(totalItemCount < 30){
                    loadMoreItems()
                }
            }
        }
    }

    abstract fun loadMoreItems()
}