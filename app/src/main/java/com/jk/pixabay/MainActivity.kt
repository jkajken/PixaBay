package com.jk.pixabay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jk.pixabay.databinding.ActivityMainBinding
import com.jk.pixabay.model.PixaModel
import com.jk.pixabay.retrofit.RetrofitService
import com.jk.pixabay.ui.adapter.PixaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())

    // private var currentInt: Int = 5
    private var page = 1
    var per_page = 3
    //private var loading = true
    //private var isScrolling = false
    //private lateinit var linearLayoutManager: LinearLayoutManager
    //private val lastVisibleItemPosition: Int
    //get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // linearLayoutManager = LinearLayoutManager(this)
        //binding.recycler.layoutManager = linearLayoutManager
        with(binding) {
            recycler.setHasFixedSize(true)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        initClicker()
        // initScrollListener()
    }

    //  private fun initScrollListener() {
    //    with(binding) {
    //      recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    //        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    //          super.onScrolled(recyclerView, dx, dy)

    //        currentInt = linearLayoutManager.childCount


    //      if (recyclerView.canScrollVertically(1)) {
    //        val totalItemCount = recycler.layoutManager!!.itemCount
    //      if (totalItemCount == lastVisibleItemPosition + 1) {
    //        if (!loading) {
    //              doRequest()
    //            page++}
    //}
    // }
    //}

    //    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    //      super.onScrollStateChanged(recyclerView, newState)
    //    if (newState == recyclerView.scrollState)
    //  {
    //     isScrolling = true
    //}
    // }
    //  })
    //}
    //}

    private fun initClicker() {
        with(binding) {
            searchBtn.setOnClickListener {
                // isScrolling = true
                per_page = 3
                page = 1
                doRequest()
            }
            nextPageBtn.setOnClickListener {
                per_page = 3
                page++
                doRequest()
            }
            updateBtn.setOnClickListener {
                per_page += 3
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        RetrofitService().api.searchImage(keyWord = searchEditText.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.let {
                            adapter = PixaAdapter(it)
                            binding.recycler.adapter = adapter
                        }
                    }


                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.d("ololo", "onResponse: ${t.message}")
                }
            })
    }
}