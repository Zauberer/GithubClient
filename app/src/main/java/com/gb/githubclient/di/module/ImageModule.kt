package com.gb.githubclient.di.module

import android.widget.ImageView
import com.gb.githubclient.mvp.image.IImageLoader
import com.gb.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}