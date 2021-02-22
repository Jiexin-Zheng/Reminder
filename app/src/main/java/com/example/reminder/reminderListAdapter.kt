package com.example.reminder

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class AdapterOfReminder(context: Context, private val resourceId: Int, data: List<BeanOfReminder>) :
        ArrayAdapter<BeanOfReminder>(context, resourceId, data) {

//    inner class ViewHolder(val timeR: TextView, val timeC: TextView, val message: EditText,
//                           val locationX: TextView, val locationY: TextView, val icon: ImageView)
    inner class ViewHolder(val timeR: TextView, val message: TextView, val icon: ImageView)

  //  @SuppressLint("UseCompatLoadingForDrawables")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val timeR: TextView = view.findViewById(R.id.reminderTime)
       //     val timeC: TextView = view.findViewById(R.id.createTimeC)
            val message: TextView = view.findViewById(R.id.reminderMessage)
        //    val locationX: TextView = view.findViewById(R.id.locationX)
         //   val locationY: TextView = view.findViewById(R.id.locationY)
            val icon: ImageView = view.findViewById(R.id.reminderIcon)
         //   viewHolder = ViewHolder(timeR,timeC,message,locationX,locationY,icon)
            viewHolder = ViewHolder(timeR, message, icon)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val bean = getItem(position)
        if (bean != null) {
            viewHolder.timeR.text = bean.reminder_time
        //    viewHolder.timeC.text = bean.creation_time
            viewHolder.message.text = Editable.Factory.getInstance().newEditable(bean.reminder_message.toString())
         //   viewHolder.locationX.text = bean.location_x.toString()
          //  viewHolder.locationY.text = bean.location_y.toString()
            viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon3))
           // viewHolder.icon.setImageResource()
        }
        return view
    }
}