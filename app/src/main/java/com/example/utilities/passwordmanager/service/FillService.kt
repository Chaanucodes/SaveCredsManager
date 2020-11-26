package com.example.utilities.passwordmanager.service

import android.os.CancellationSignal
import android.service.autofill.*
import com.example.utilities.passwordmanager.service.parser.StructureParser
import com.example.utilities.passwordmanager.service.response.ResponseBuilder
import dagger.android.AndroidInjection
import javax.inject.Inject

class FillService : AutofillService() {

    @Inject
    lateinit var parser: StructureParser

    @Inject
    lateinit var responseBuilder: ResponseBuilder

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onFillRequest(request: FillRequest, cancellationSignal: CancellationSignal, callback: FillCallback) {
        val structure = request.fillContexts[request.fillContexts.size - 1].structure
        val appPackageName = structure.activityComponent.packageName

        val fields = parser.parse(structure)
        callback.onSuccess(responseBuilder.createResponse(this, appPackageName, fields))
    }

    override fun onSaveRequest(request: SaveRequest, callback: SaveCallback) {
    }
}