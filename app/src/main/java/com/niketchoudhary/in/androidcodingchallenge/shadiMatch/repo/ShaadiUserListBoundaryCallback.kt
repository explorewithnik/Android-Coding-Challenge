package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.repo

import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable
import com.niketchoudhary.`in`.androidcodingchallenge.utility.*
import retrofit2.Call

class ShaadiUserListBoundaryCallback(
    private val handleResponse: (CommonApiResponse<List<ShadiMatchDataTable>>?) -> Unit,
    appExecutors: AppExecutors,
    private val webService: WebService
) : DataBoundBoundaryCallback<ShadiMatchDataTable, CommonApiResponse<List<ShadiMatchDataTable>>>(
    appExecutors = appExecutors
) {

    override fun zeroItemLoaded(): Call<CommonApiResponse<List<ShadiMatchDataTable>>> {

        return webService.fetchOnlineInspectionList(
            url = CommonURL.FETCH_SHAADI_USER,
            limit = 0
        )
    }

    override fun itemAtEndLoaded(item: ShadiMatchDataTable): Call<CommonApiResponse<List<ShadiMatchDataTable>>> {
        return webService.fetchOnlineInspectionList(
            url = CommonURL.FETCH_SHAADI_USER,
            limit = (item.indexInResponse)
        )

    }

    override fun handleAPIResponse(response: CommonApiResponse<List<ShadiMatchDataTable>>?) {
        handleResponse(response)
    }
}