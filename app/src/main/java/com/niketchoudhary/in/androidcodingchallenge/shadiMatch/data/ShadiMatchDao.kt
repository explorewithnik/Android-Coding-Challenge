package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface ShadiMatchDao {
    @Query("SELECT MAX(indexInResponse) + 1 FROM ShadiMatchDataTable where upload = 1")
    fun getNextIndex(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInspectionDataList(list: List<ShadiMatchDataTable>?)

    @Transaction
    fun insertInspectionDataListPagination(list: List<ShadiMatchDataTable>?) {
        val start = getNextIndex()
        val items = list?.mapIndexed { index, child ->
            child.indexInResponse = start + index
            child.upload = 1
            child
        }
        insertInspectionDataList(items)
    }

    @Query("SELECT * FROM ShadiMatchDataTable WHERE upload=:id ORDER BY indexInResponse")
    fun loadUserDataList(id: Int): DataSource.Factory<Int, ShadiMatchDataTable>

    @Query("DELETE from ShadiMatchDataTable where selectionStatus != null")
    fun deleteList()

    @Query("UPDATE ShadiMatchDataTable SET selectionStatus=:selectionStatus WHERE cell=:id")
    fun updateMatchStatus(id: String, selectionStatus: String)

}