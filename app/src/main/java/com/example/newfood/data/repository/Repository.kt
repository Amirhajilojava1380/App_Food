package com.example.newfood.data.repository

import com.example.newfood.data.datasource.LocalDataSource
import com.example.newfood.data.datasource.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(

    remoteDataSource: RemoteDataSource ,
    localDataSource: LocalDataSource

) {

    val remote = remoteDataSource
    val local  = localDataSource
}