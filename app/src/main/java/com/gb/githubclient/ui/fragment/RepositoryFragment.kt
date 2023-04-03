package com.gb.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gb.githubclient.App
import com.gb.githubclient.databinding.FragmentRepositoryBinding
import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.presenter.RepositoryPresenter
import com.gb.githubclient.mvp.view.RepositoryView
import com.gb.githubclient.ui.activity.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    @Inject lateinit var router: Router

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }

            App.instance.appComponent.inject(this)
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository

        RepositoryPresenter(repository, router)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {}

    override fun setId(text: String) {
        binding.tvId.text = text
    }

    override fun setTitle(text: String) {
        binding.tvTitle.text = text
    }

    override fun setForksCount(text: String) {
        binding.tvForksCount.text = text
    }

    override fun backPressed() = presenter.backPressed()
}