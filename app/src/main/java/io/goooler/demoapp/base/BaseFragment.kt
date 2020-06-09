package io.goooler.demoapp.base

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.goooler.demoapp.util.ToastUtil

/**
 * Fragment 基类，封装通用方法
 */
abstract class BaseFragment : Fragment() {

    /**
     * 调用 activity 的返回
     */
    protected fun onBackPressed() {
        activity?.onBackPressed()
    }

    /**
     * @param containerId       容器 id
     * @param fragment          要添加的 fragment
     * @param isAddToBackStack  将要添加的 fragment 是否要添加到返回栈
     * @param tag               fragment 的 tag
     */
    protected fun addFragment(@IdRes containerId: Int, fragment: Fragment, isAddToBackStack: Boolean = false, tag: String? = null) {
        getFragmentTransaction(isAddToBackStack, tag).add(containerId, fragment, tag).commit()
    }

    /**
     * @param containerId       容器 id
     * @param fragment          要替换的 fragment
     * @param isAddToBackStack  将要替换的 fragment 是否要添加到返回栈
     * @param tag               fragment 的 tag
     */
    protected fun replaceFragment(@IdRes containerId: Int, fragment: Fragment, isAddToBackStack: Boolean = true, tag: String? = null) {
        getFragmentTransaction(isAddToBackStack, tag).replace(containerId, fragment, tag).commit()
    }

    private fun getFragmentTransaction(isAddToBackStack: Boolean, tag: String?): FragmentTransaction {
        return childFragmentManager.beginTransaction().apply {
            if (isAddToBackStack) {
                addToBackStack(tag)
            }
        }
    }

    protected fun <T : BaseViewModel> getViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this@BaseFragment).get(modelClass).apply {
            lifecycle.addObserver(this)
            observeVm(this)
        }
    }

    protected fun <T : BaseViewModel> getViewModelOfActivity(modelClass: Class<T>): T {
        return ViewModelProvider(requireActivity()).get(modelClass).apply {
            lifecycle.addObserver(this)
            observeVm(this)
        }
    }

    private fun observeVm(vm: BaseViewModel) {
        vm.toast.observe(this@BaseFragment, Observer {
            showToast(it)
        })
    }

    protected fun showToast(@StringRes textId: Int) {
        ToastUtil.showToast(textId)
    }

    protected fun showToast(text: String) {
        ToastUtil.showToast(text)
    }
}
