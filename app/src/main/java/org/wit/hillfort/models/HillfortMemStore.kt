package org.wit.hillfort.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HillfortMemStore : HillfortStore, AnkoLogger {

    val hillforts = ArrayList<HillfortModel>()

    override fun findAll(): ArrayList<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = getId()
        hillforts.add(hillfort)
        logAll()
    }

    override fun update(hillfort: HillfortModel){
        var foundHillfort: HillfortModel? = hillforts.find { h -> h.id == hillfort.id}
        if (foundHillfort != null){
            foundHillfort.name = hillfort.name
            foundHillfort.description = hillfort.description
            foundHillfort.images = ArrayList(hillfort.images)
            foundHillfort.lat = hillfort.lat
            foundHillfort.lng = hillfort.lng
            foundHillfort.zoom = hillfort.zoom
            foundHillfort.visited = hillfort.visited
            foundHillfort.notes = hillfort.notes
            logAll()
        }
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
    }

    fun logAll() {
        hillforts.forEach{ info("${it}") }
    }
}