package io.goooler.demoapp.main.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.goooler.demoapp.adapter.rv.paging.BasePagingSource
import io.goooler.demoapp.common.base.BaseThemeViewModel
import io.goooler.demoapp.common.network.RetrofitHelper
import io.goooler.demoapp.common.type.CommonConstants
import io.goooler.demoapp.common.util.RoomHelper
import io.goooler.demoapp.main.model.MainCommonVhModel
import io.goooler.demoapp.main.repository.MainCommonRepository
import kotlinx.coroutines.flow.Flow

class MainPagingViewModel(application: Application) : BaseThemeViewModel(application) {

    private val repository = MainCommonRepository(RetrofitHelper.create(), RoomHelper.create())

    val listData: Flow<PagingData<MainCommonVhModel>> =
        Pager(PagingConfig(CommonConstants.DEFAULT_PAGE_SIZE)) {
            DataSource()
        }.flow.cachedIn(viewModelScope)

    private inner class DataSource : BasePagingSource<MainCommonVhModel>() {
        override suspend fun fetchListData(page: Int): List<MainCommonVhModel> {
            return repository.getRepoListWithCr("google", page)
                .map { MainCommonVhModel.Repo(it.owner?.avatarUrl, it.name) }
        }
    }
}