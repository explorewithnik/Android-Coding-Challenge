package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.repo

import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDao
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable
import com.niketchoudhary.`in`.androidcodingchallenge.utility.*
import retrofit2.Call
import javax.inject.Inject

class ShaadiRepository @Inject constructor(
    private val webservice: WebService,
    private val executor: AppExecutors,
    private val dao: ShadiMatchDao,
    val pagedListConfig: PagedList.Config

) : PaginationRepository<ShadiMatchDataTable, CommonApiResponse<List<ShadiMatchDataTable>>>(
    executors = executor,
    pagedListConfig = pagedListConfig
) {

    override fun boundaryCallback(search: String?): DataBoundBoundaryCallback<ShadiMatchDataTable, CommonApiResponse<List<ShadiMatchDataTable>>> {
        return ShaadiUserListBoundaryCallback(
            handleResponse = this::insertResultIntoDb,
            appExecutors = executor,
            webService = webservice
        )
    }

    override fun dataSourceFactory(search: String?) = dao.loadUserDataList(id = 1)

    private fun insertResultIntoDb(body: CommonApiResponse<List<ShadiMatchDataTable>>?) {
        dao.insertInspectionDataListPagination(list = body?.results)
    }

    @MainThread
    fun fetchAllUserList(): Listing<ShadiMatchDataTable> {
        return response("")
    }

    fun deleteList() {
        executor.diskIO().execute { dao.deleteList() }
    }

    fun updateMatchStatus(id: String, selectionStatus: String) {
        executor.diskIO()
            .execute { dao.updateMatchStatus(id = id, selectionStatus = selectionStatus) }
    }

    override fun refreshOperation(response: CommonApiResponse<List<ShadiMatchDataTable>>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshAPI(search: String?): Call<CommonApiResponse<List<ShadiMatchDataTable>>> {
        TODO("Not yet implemented")
    }


}
