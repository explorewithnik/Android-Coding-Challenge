package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import androidx.paging.PagedList
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDao
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.repo.ShaadiRepository
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AppExecutors
import com.niketchoudhary.`in`.androidcodingchallenge.utility.WebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideShaadiRepository(
        dao: ShadiMatchDao,
        webservice: WebService,
        executor: AppExecutors,
        pagedListConfig: PagedList.Config
    ): ShaadiRepository {
        return ShaadiRepository(
            dao = dao,
            webservice = webservice,
            executor = executor,
            pagedListConfig = pagedListConfig
        )
    }

}