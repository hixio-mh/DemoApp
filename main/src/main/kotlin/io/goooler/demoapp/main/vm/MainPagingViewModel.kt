package io.goooler.demoapp.main.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import io.goooler.demoapp.adapter.rv.paging.BasePagingSource
import io.goooler.demoapp.common.base.BaseThemeViewModel
import io.goooler.demoapp.common.type.CommonConstants
import io.goooler.demoapp.main.model.MainCommonVhModel
import io.goooler.demoapp.main.repository.MainCommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class MainPagingViewModel @ViewModelInject constructor(private val repository: MainCommonRepository) :
  BaseThemeViewModel() {
  private val removedItemsFlow = MutableStateFlow(emptyList<MainCommonVhModel>())
  private val removeItemsSet = mutableSetOf<MainCommonVhModel>()

  val listData: Flow<PagingData<MainCommonVhModel>> =
    Pager(PagingConfig(CommonConstants.DEFAULT_PAGE_SIZE)) {
      DataSource()
    }.flow
      .cachedIn(viewModelScope)
      .combine(removedItemsFlow) { paging, removed ->
        paging.filter { it !in removed }
      }

  fun removeItem(item: MainCommonVhModel) {
    removeItemsSet.add(item)
    removedItemsFlow.value = removeItemsSet.toList()
  }

  private inner class DataSource : BasePagingSource<MainCommonVhModel>() {
    override suspend fun fetchListData(page: Int): List<MainCommonVhModel> {
      return repository.getRepoListWithCr("google", page)
        .map { MainCommonVhModel.Repo(it.owner?.avatarUrl, it.name) }
    }
  }
}
