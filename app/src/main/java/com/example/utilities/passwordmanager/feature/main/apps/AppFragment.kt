package com.example.utilities.passwordmanager.feature.main.apps

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utilities.passwordmanager.R
import com.example.utilities.passwordmanager.base.BaseFragment
import com.example.utilities.passwordmanager.databinding.FragmentAppBinding
import com.example.utilities.passwordmanager.feature.common.DividerDecorator
import com.example.utilities.passwordmanager.feature.main.common.PackageViewModel
import com.example.utilities.passwordmanager.feature.main.view.ViewPackageDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AppFragment : BaseFragment<FragmentAppBinding>(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: PackageViewModel.Factory

    @Inject
    lateinit var appAdapter: AppAdapter

    override fun setupDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun setupBindingAndViewModel(binding: FragmentAppBinding) {
        val vm = ViewModelProviders.of(activity!!, factory)[PackageViewModel::class.java]
        binding.viewModel = vm
        binding.list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerDecorator(ContextCompat.getDrawable(context, R.drawable.list_divider)!!))
            adapter = appAdapter
        }

        appAdapter.listener = {
            vm.setCurrentPackage(it)
            val dialog = ViewPackageDialog()
            dialog.show(fragmentManager!!, "view")
        }

        vm.copiedToClipboard.observe(this, Observer {
            if (it) Toast.makeText(context, getString(R.string.message_clipboard), Toast.LENGTH_SHORT).show()
        })

        vm.list.observe(this, Observer {
            appAdapter.addPackages(it)
        })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    override val layoutId: Int = R.layout.fragment_app
}