package com.gb.githubclient.mvp.presenter

import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(val githubRepository: GithubRepository, val router: Router)
    : MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount((githubRepository.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy presenter")
    }
}