package io.goooler.demoapp.base.core

import android.annotation.SuppressLint
import android.app.Application
import io.goooler.demoapp.base.util.CrashHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 封装通用方法和一些初始化的动作
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    /**
     * 应用启动时初始化
     */
    @SuppressLint("CheckResult")
    private fun initData() {
        context = this
        CrashHandler.init()
        GlobalScope.launch(Dispatchers.IO) {
            delay(2000)
            initLater()
        }
    }

    protected open fun initLater() {}

    companion object {
        /**
         * 获取全局 context
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: BaseApplication
            private set
    }
}