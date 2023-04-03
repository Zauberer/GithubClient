package com.gb.githubclient.mvp.presenter

import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.repo.IGithubUsersRepo
import com.gb.githubclient.mvp.presenter.list.IUserListPresenter
import com.gb.githubclient.mvp.view.UsersView
import com.gb.githubclient.mvp.view.list.UserItemView
import com.gb.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]

            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}