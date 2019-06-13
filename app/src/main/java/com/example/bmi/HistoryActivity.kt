package com.example.bmi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.example.bmi.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.activity_history.*
import android.support.v7.widget.DividerItemDecoration
import java.util.*
import kotlin.collections.ArrayList


class HistoryActivity : AppCompatActivity() {

    lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        var history: ArrayList<String> = ArrayList()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val size = sp.getInt("HistorySize", 0)
        for (i in 0 until size) {
            val string = sp.getString("Entry_$i", null)
            if(string != null) {
                history.add(string)
            }
        }
        history.reverse()

        adapter = HistoryAdapter(this, history)
        historyRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        historyRecyclerView.layoutManager = layoutManager
        historyRecyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(
            historyRecyclerView.context,
            layoutManager.orientation
        )
        historyRecyclerView.addItemDecoration(dividerItemDecoration)
    }
}
