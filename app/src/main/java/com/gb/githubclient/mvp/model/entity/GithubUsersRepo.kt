package com.gb.githubclient.mvp.model.entity

class GithubUsersRepo {
    private val users = listOf (
        GithubUser("", "login1"),
        GithubUser("","login2"),
        GithubUser("","login3"),
        GithubUser("","login4"),
        GithubUser("","login5")
    )

    fun getUsers() : List<GithubUser> {
        return users
    }
}