package com.cocoslime.recyclerviewsample

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cocoslime.recyclerviewsample.databinding.ListItemCommitBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.CommitViewHolder>() {
    var commitList: List<GitCommit> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemCommitBinding.inflate(layoutInflater, parent, false)

        return CommitViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val item = commitList[position]
        holder.bind(item, CommitClickListener {
            it.showSubMessage = !it.showSubMessage
            notifyItemChanged(position)
        })
    }

    override fun getItemCount(): Int {
        return commitList.size
    }

    class CommitViewHolder(private val binding: ListItemCommitBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: GitCommit, clickListener : CommitClickListener) {
            binding.commit = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class CommitClickListener(val clickListener : (commit:GitCommit) -> Unit ) {
    fun onClick(commit : GitCommit){
        clickListener(commit)
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<GitCommit>() {
    // For add, remove, move
    override fun areItemsTheSame(oldItem: GitCommit, newItem: GitCommit): Boolean {
        return oldItem.hash == newItem.hash
    }

    // For Update
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GitCommit, newItem: GitCommit): Boolean {
        return oldItem == newItem
    }
}
