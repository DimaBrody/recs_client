package com.test.books.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.books.R
import com.test.books.data.model.profile.ProfileItem
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter(
    private val items: MutableList<ProfileItem> = mutableListOf()
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder =
        ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile, parent, false
            )
        )


    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProfileItem) = itemView.apply {
            item_profile_icon.setImageDrawable(ContextCompat.getDrawable(context, item.icon))
            item_profile_icon.setColorFilter(
                ContextCompat.getColor(context, item.iconTint),
                android.graphics.PorterDuff.Mode.SRC_IN
            )

            item_profile_text.text = item.text
            if(item.icon == R.drawable.ic_exit)
                item_profile_text.setTextColor(ContextCompat.getColor(context, item.iconTint))

            setOnClickListener {
                item.callback.invoke()
            }
        }
    }


}