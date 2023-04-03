package com.gb.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.githubclient.databinding.ItemRepositoryBinding
import com.gb.githubclient.databinding.ItemUserBinding
import com.gb.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.gb.githubclient.mvp.view.list.RepositoryItemView
import com.gb.githubclient.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer

class ReposotoriesRVAdapter(val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<ReposotoriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(val vb: ItemRepositoryBinding) : RecyclerView.ViewHolder(vb.root),
        RepositoryItemView {
        override var pos = -1

        override fun setName(text: String) = with(vb) {
            tvName.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ReposotoriesRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

}