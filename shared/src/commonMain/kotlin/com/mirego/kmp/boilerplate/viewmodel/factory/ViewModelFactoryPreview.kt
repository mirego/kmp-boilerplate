package com.mirego.kmp.boilerplate.viewmodel.factory

import com.mirego.kmp.boilerplate.usecase.preview.UseCaseFactoryPreview
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogButtonData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.CoroutineScopeManagerImpl
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManagerImpl
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen1ViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen2ViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.screen.Screen3ViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab1ViewModelImpl
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab2ViewModelImpl
import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.CoroutineScope

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewModelFactoryPreview(
    i18N: I18N,
    private val useCaseFactoryPreview: UseCaseFactoryPreview = UseCaseFactoryPreview()
) : ViewModelFactory {

    private val coroutineScopeManager = CoroutineScopeManagerImpl()

    private val navigationManager = DemoNavigationManagerImpl(coroutineScopeManager)

    fun createApplication() = ApplicationViewModelImpl(
        navigationManager = navigationManager,
        coroutineScopeManager = coroutineScopeManager,
        viewModelFactory = this,
        coroutineScope = coroutineScopeManager.createMainThreadCoroutineScope()
    )

    fun createTab1() = Tab1ViewModelImpl(
        navigationManager = navigationManager,
        coroutineScope = coroutineScopeManager.createMainThreadCoroutineScope()
    )

    override fun createTab1(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope) = createTab1()

    fun createTab2() = Tab2ViewModelImpl(
        navigationManager = navigationManager,
        coroutineScope = coroutineScopeManager.createMainThreadCoroutineScope()
    )

    override fun createTab2(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope) = createTab2()

    fun createScreen1() = Screen1ViewModelImpl("1", navigationManager, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createScreen1(uniqueId:String, navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope) = createScreen1()

    fun createScreen2() = Screen2ViewModelImpl(navigationManager, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createScreen2(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope) = createScreen2()

    fun createScreen3() = Screen3ViewModelImpl(navigationManager, coroutineScopeManager.createMainThreadCoroutineScope())

    override fun createScreen3(navigationManager: DemoNavigationManager, coroutineScope: CoroutineScope) = createScreen3()

    fun createDialog() = DialogViewModelImpl(
        navigationManager = navigationManager,
        navigationData = DialogNavigationData(
            title = "Dialog Title",
            message = "Dialog message message message message",
            buttons = listOf(
                DialogButtonData(
                    title = "Button 1",
                    action = {}
                ),
                DialogButtonData(
                    title = "Button 2",
                    action = {}
                )
            ),
        ),
        coroutineScope = coroutineScopeManager.createMainThreadCoroutineScope()
    )

    override fun createDialog(
        navigationData: DialogNavigationData,
        navigationManager: DemoNavigationManager,
        coroutineScope: CoroutineScope
    ) = createDialog()
}
