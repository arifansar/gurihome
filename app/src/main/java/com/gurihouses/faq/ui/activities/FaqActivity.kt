package com.gurihouses.faq.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gurihouses.R
import com.gurihouses.databinding.ActivityFaqBinding
import com.gurihouses.faq.adapter.FaqAdapter
import com.gurihouses.faq.model.FaqData

class FaqActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        getAllData()
    }

    private fun getAllData() {

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<FaqData>()
        for (i in 1..6) {
            data.add(
                FaqData(
                    "How can i use GuriHouses?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ultrices fusce sit nunc, et amet, diam nunc. Vitae pellentesque donec donec imperdiet fringilla faucibus euismod. Etiam enim interdum ultricies quisque purus. In bibendum convallis non tellus. Hendrerit morbi amet, nibh tincidunt id non.\n" +
                            ". Suscipit tincidunt condimentum."
                )
            )
        }
        val adapter = FaqAdapter(data)
        recyclerview.adapter = adapter

    }
}