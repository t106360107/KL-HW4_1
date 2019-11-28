package com.example.hwk4_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trans_list.view.*

class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Item>()
    private val items2 = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imgArray = resources.obtainTypedArray(R.array.imgArray)
        val nameArray = resources.getStringArray(R.array.nameArray)
        for(i in 0 until imgArray.length())
            items.add(Item(imgArray.getResourceId(i,0), nameArray[i]))
        imgArray.recycle()
        val imgArray2 = resources.obtainTypedArray(R.array.imgArray2)
        val nameArray2 = resources.getStringArray(R.array.nameArray2)
        for(i in 0 until imgArray.length())
            items2.add(Item(imgArray2.getResourceId(i,0), nameArray2[i]))
        imgArray2.recycle()
        spinner.adapter = MyAdapter(R.layout.trans_list, items2)
        gridView.numColumns = 3
        gridView.adapter = MyAdapter(R.layout.cubee_list, items)

        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            arrayListOf("項目1","項目2","項目3","項目4","項目5","項目6"))
    }
}

data class Item(
    val photo: Int,
    val name: String
)


class MyAdapter constructor(private val layout: Int, private val data: ArrayList<Item>)
    : BaseAdapter() {

    override fun getCount() = data.size
    override fun getItem(position: Int) = data[position]
    override fun getItemId(position: Int) = 0L
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = View.inflate(parent.context, layout, null)
        view.imageView.setImageResource(data[position].photo)
        view.name.text = data[position].name

        return view
    }
}
