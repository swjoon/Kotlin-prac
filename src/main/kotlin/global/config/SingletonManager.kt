package org.SBB.global.config

import org.SBB.domain.controller.AppController
import org.SBB.domain.repository.AppFileRepository
import org.SBB.domain.service.AppService

object SingletonManager {
    val repository: AppFileRepository by lazy { AppFileRepository() }
    val service: AppService by lazy { AppService(repository) }
    val controller: AppController by lazy { AppController(service) }
}