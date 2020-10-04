package com.saka.android.imagepicker

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.saka.android.imagepicker.sheet.ImagePicker

class MainActivity : AppCompatActivity(), MainEvent, ImagePicker.ImageListener {

    private lateinit var clickEvent: MainEvent
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickEvent = this
        findViewById<TextView>(R.id.textSinglePicker).setOnClickListener {
            clickEvent.onSinglePickerClick()
        }
        findViewById<TextView>(R.id.textMultiPicker).setOnClickListener {
            clickEvent.onMultiPickerClick()
        }

        adapter = Adapter()
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

    override fun onSinglePickerClick() {
        ImagePicker.Builder("com.saka.android.imagepicker.fileprovider")
            .setListener(this)
            .setSpanCount(3)
            .build()
            .show(supportFragmentManager, "picker")
    }

    override fun onMultiPickerClick() {
        ImagePicker.Builder("com.saka.android.imagepicker.fileprovider")
            .setListener(this)
            .isMultiSelect()
            .setSpanCount(3)
            .setMinSelectCount(3)
            .build()
            .show(supportFragmentManager, "picker2")
    }

    override fun onCancelled(isMultiSelecting: Boolean) {
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
    }

    override fun onSingleSelect(uri: Uri?) {
        adapter.setImage(listOf(uri!!))
    }

    override fun onMultipleSelect(uriList: List<Uri>?) {
        adapter.setImage(uriList)
    }
}

class Adapter(
    private var list: List<Uri> = emptyList()
) : RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ImageView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 480)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setImage(newList: List<Uri>?) {
        list = newList.orEmpty()
        notifyDataSetChanged()
    }
}

class Holder(
    private val imageView: ImageView
) : RecyclerView.ViewHolder(imageView) {

    fun bind(item: Uri) {
        imageView.setImageURI(item)
    }
}

interface MainEvent {
    fun onSinglePickerClick()
    fun onMultiPickerClick()
}
