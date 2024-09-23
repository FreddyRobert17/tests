package com.app.myapplication2

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(
    private val words: MutableList<String>
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var itemTouchHelper: ItemTouchHelper? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordTextView: TextView = itemView.findViewById(R.id.wordTextView)

        init {
            // Habilitar el drag and drop en cada item
            itemView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    // Iniciar el arrastre al presionar el item
                    itemTouchHelper?.startDrag(this)
                }
                false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordTextView.text = words[position]
    }

    override fun getItemCount() = words.size

    // Función para mover el item
    fun swapItems(fromPosition: Int, toPosition: Int) {
        val temp = words[fromPosition]
        words[fromPosition] = words[toPosition]
        words[toPosition] = temp
        notifyItemMoved(fromPosition, toPosition)
    }

    // Método para establecer el ItemTouchHelper
    fun setItemTouchHelper(itemTouchHelper: ItemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper
    }

    fun isInDesiredOrder(desiredOrder: List<String>): Boolean {
        return words == desiredOrder
    }
}
