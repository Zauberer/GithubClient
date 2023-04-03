package com.gb.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.githubclient.App
import com.gb.githubclient.databinding.FragmentUsersBinding
import com.gb.githubclient.mvp.model.api.ApiHolder
import com.gb.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.gb.githubclient.mvp.model.entity.room.Database
import com.gb.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.gb.githubclient.mvp.presenter.UsersPresenter
import com.gb.githubclient.mvp.view.UsersView
import com.gb.githubclient.ui.activity.BackButtonListener
import com.gb.githubclient.ui.adapter.UsersRVAdapter
import com.gb.githubclient.ui.image.GlideImageLoader
import com.gb.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = UsersFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
             App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        // TODO
    }

    override fun backPressed() = presenter.backPressed()

}