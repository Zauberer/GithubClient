package com.gb.githubclient.mvp.presenter

import com.gb.githubclient.di.repository.IRepositoryScopeContainer
import com.gb.githubclient.mvp.model.entity.GithubRepository
import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.gb.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.gb.githubclient.mvp.view.UserView
import com.gb.githubclient.mvp.view.list.RepositoryItemView
import com.gb.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(val user: GithubUser) :
    MvpPresenter<UserView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens
    @Inject
    lateinit var repositoryScopeContainer: IRepositoryScopeContainer

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        user.login?.let { viewState.setLogin(it) }

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.repository(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
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
        repositoryScopeContainer.releaseRepositoryScope()
        super.onDestroy()
    }
}