package com.example.chunggo803

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { ChatListViewModel() }

    // single { SampleRepository() }
    // single { SampleController() }
}