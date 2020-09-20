package com.mo.aad.features.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.aad.R
import com.mo.aad.features.main.data.LearningHoursUser
import com.mo.aad.features.main.data.SkillsScoreUser
import com.mo.aad.features.main.ui.learning.LearningViewHolder
import com.mo.aad.features.main.ui.skilliq.SkillIQViewHolder
import kotlinx.android.synthetic.main.layout_item.view.*

class BaseAdapter<T>(list: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val asyncListDiffer = AsyncListDiffer(this, DiffUtilComparator<T>())

    init {
        asyncListDiffer.submitList(list)
    }

    /**
     * instantiate BaseAdapter as singleton instance
     * and used this method when updating data.
     */
    fun updateList(list: List<T>) {
        asyncListDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return when {
            asyncListDiffer.currentList.all { it is SkillsScoreUser } -> {
                SkillIQViewHolder(view)
            }
            else -> LearningViewHolder(view)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    internal interface Binder<T> {
        fun bind(item: T)
    }
}

class DiffUtilComparator<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        when (oldItem) {
            is LearningHoursUser -> {
                return (oldItem as LearningHoursUser).badgeUrl == (newItem as LearningHoursUser).badgeUrl
            }
            is SkillsScoreUser -> {
                return (oldItem as SkillsScoreUser).badgeUrl == (newItem as SkillsScoreUser).badgeUrl
            }
        }
        throw ClassCastException()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        when (oldItem) {
            is LearningHoursUser -> {
                return (oldItem as LearningHoursUser) == (newItem as LearningHoursUser)
            }
            is SkillsScoreUser -> {
                return (oldItem as SkillsScoreUser) == (newItem as SkillsScoreUser)
            }
        }
        throw ClassCastException()
    }
}