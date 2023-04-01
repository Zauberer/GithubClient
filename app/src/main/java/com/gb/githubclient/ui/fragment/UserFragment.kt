package com.gb.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gb.githubclient.App
import com.gb.githubclient.databinding.FragmentUserBinding
import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.presenter.UserPresenter
import com.gb.githubclient.mvp.view.UserView
import com.gb.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user, App.instance.router)
    }

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        // Nothing to do
    }

    override fun setLogin(text: String) {
        binding.tvLogin.text = text
    }

    override fun updateList() {
        // TODO: List of repos
    }

    override fun release() {
        // TODO:
    }

    override fun backPressed() = presenter.backPressed()
}