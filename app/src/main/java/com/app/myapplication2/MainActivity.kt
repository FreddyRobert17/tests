package com.app.myapplication2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    private val desiredOrder = listOf("I", "have", "to", "get", "up", "early")
    private lateinit var lottieAnimationView: LottieAnimationView

    // Definición del callback para el ItemTouchHelper
    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val adapter = recyclerView.adapter as WordAdapter
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            adapter.swapItems(fromPosition, toPosition)

            if (adapter.isInDesiredOrder(desiredOrder)) {
                // Mostrar un mensaje cuando las palabras estén en el orden correcto
                lottieAnimationView.visibility = View.VISIBLE
                lottieAnimationView.playAnimation()
            }

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // No implementamos swipe
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val words = mutableListOf("I", "have", "to", "get", "up", "early")
        lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)


        val adapter = WordAdapter(words)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Vincular ItemTouchHelper al RecyclerView
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Pasar el ItemTouchHelper al adapter
        adapter.setItemTouchHelper(itemTouchHelper)
    }
}
