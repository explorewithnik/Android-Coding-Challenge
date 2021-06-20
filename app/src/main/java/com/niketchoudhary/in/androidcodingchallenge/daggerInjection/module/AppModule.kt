package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AppExecutors

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        (ViewModelModule::class),
        (NetworkModule::class),
        (RepositoryModule::class),
        (DaoModule::class)]
)
class AppModule{
        /**
         * Pagination injection
         */
        @Provides
        @Singleton
        fun providePagingConfig(): PagedList.Config {
                return PagedList.Config.Builder()
                        .setPageSize(10)
                        .setInitialLoadSizeHint(30)
                        .setPrefetchDistance(10)
                        .setEnablePlaceholders(true)
                        .build()
        }


        @Provides
        @Singleton
        fun providePageBuilder(
                source: DataSource.Factory<Int, Any>,
                config: PagedList.Config,
                executor: AppExecutors
        ): LiveData<PagedList<Any>> {
                return LivePagedListBuilder(source, config)
                        .setFetchExecutor(executor.diskIO())
                        .build()
        }
}