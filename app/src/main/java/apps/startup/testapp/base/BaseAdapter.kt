package apps.startup.testapp.base


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

import apps.startup.testapp.BR


abstract class BaseAdapter : RecyclerView.Adapter<BaseAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(
                    getDataAtPosition(adapterPosition),
                    adapterPosition
                )
            }
        }

        fun bind(obj: Any) {
            binding.setVariable(apps.startup.testapp.BR.obj, obj)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create repair new view
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            getLayoutIdForType(viewType),
            parent,
            false
        )
        // set the view's size, margins, paddings and layout parameters
        editHeightWidthItem(binding.root, parent)
        return MyViewHolder(binding)
    }

    // Replace the contents of repair view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getDataAtPosition(position))
    }

    abstract fun getDataAtPosition(position: Int): Any

    abstract fun getLayoutIdForType(viewType: Int): Int

    abstract fun onItemClick(`object`: Any, position: Int)

    abstract fun editHeightWidthItem(view: View, parent: ViewGroup)

}