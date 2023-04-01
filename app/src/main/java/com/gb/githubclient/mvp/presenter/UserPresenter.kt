package com.gb.githubclient.mvp.presenter

import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.entity.GithubUsersRepo
import com.gb.githubclient.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val user: GithubUser, val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setLogin(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}