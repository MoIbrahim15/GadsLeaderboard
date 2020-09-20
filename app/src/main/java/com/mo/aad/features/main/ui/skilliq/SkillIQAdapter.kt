package com.mo.aad.features.main.ui.skilliq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.aad.R
import com.mo.aad.features.main.data.SkillsScoreUser
import com.mo.aad.features.main.ui.BaseAdapter
import kotlinx.android.synthetic.main.layout_item.view.*

class SkillIQAdapter(val items: List<SkillsScoreUser>) : RecyclerView.Adapter<SkillIQViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQViewHolder {
        return SkillIQViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SkillIQViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SkillIQViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView),
    BaseAdapter.Binder<SkillsScoreUser> {
    override fun bind(item: SkillsScoreUser) = with(itemView){
        Glide.with(itemView)
            .load(item.badgeUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemView.ivLearning)

        itemView.tvTitle.text = item.name
        itemView.tvDesc.text = String.format(resources.getString(R.string.skill_hours), item.score, item.country)
    }
}
