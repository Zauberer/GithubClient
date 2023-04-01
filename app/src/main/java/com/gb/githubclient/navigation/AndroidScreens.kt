package com.gb.githubclient.navigation

import com.gb.githubclient.mvp.model.entity.GithubUser
import com.gb.githubclient.ui.fragment.UserFragment
import com.gb.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}