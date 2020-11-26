package com.example.utilities.passwordmanager.core.packages

import com.example.utilities.passwordmanager.model.Package
import io.reactivex.Single

interface PackageManager {
    fun getPackages(): Single<List<Package>>

    fun getPackageFromName(packageName: String) : Single<Package>
}
