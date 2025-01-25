package com.mmag.stephenking.domain.model.mapper

import com.mmag.stephenking.data.network.model.VillainResponse
import com.mmag.stephenking.domain.model.Villain

fun VillainResponse.toDomainModel(): Villain {
    return Villain(
        name = this.name,
        url = this.url
    )
}

fun List<VillainResponse>.toDomainModel(): List<Villain> = this.map {
    it.toDomainModel()
}