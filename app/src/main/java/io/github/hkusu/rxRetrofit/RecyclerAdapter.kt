package io.github.hkusu.rxRetrofit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var superItems: List<JsonZones_Base> = arrayListOf()

    var onZoneClickListener : OnZoneClickListener ? = null

    public fun setItems(items: List<JsonZones_Base>) {
        if (items.isNotEmpty()) {
            this.superItems = items
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemId: TextView
        var itemIndex: TextView
        var itemName: TextView
        var itemType: TextView
        var itemImage: ImageView

        init {
            itemId = itemView.findViewById(R.id.item_id)
            itemIndex = itemView.findViewById(R.id.item_index)
            itemName = itemView.findViewById(R.id.item_name)
            itemType = itemView.findViewById(R.id.item_type)
            itemImage = itemView.findViewById(R.id.item_image)
        }

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemId.text = superItems.get(i).id.toString()
        viewHolder.itemIndex.text = superItems.get(i).index
        viewHolder.itemName.text = superItems.get(i).name
        viewHolder.itemType.text = superItems.get(i).type

        viewHolder.itemView.setOnClickListener {
            onZoneClickListener?.onZoneClick(superItems.get(i))
        }
        //  viewHolder.itemImage.text = details[i]
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return superItems.size
    }
}


interface OnZoneClickListener {
    fun onZoneClick(zone: JsonZones_Base)
}