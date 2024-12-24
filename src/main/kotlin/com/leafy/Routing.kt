package com.leafy

import com.example.connectToPostgres
import com.leafy.db.PlantService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    val connection = connectToPostgres()
    val plantService = PlantService(connection)

    routing {
        get("/listPlant") {
            val page = call.request.queryParameters["page"]?.toIntOrNull()
            if (page == null || page < 1) {
                return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid or missing 'page' query parameter"
                )
            }
            try {
                val plants = plantService.fetchPlantsByPageSortedById(page)
                call.respondText(
                    text = Json.encodeToString(plants),
                    contentType = ContentType.Application.Json
                )
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error fetching plants: ${e.message}")
            }
        }

        get("/searchPlant") {
            val name = call.request.queryParameters["name"]
            val page = call.request.queryParameters["page"]?.toIntOrNull()
            if (name.isNullOrEmpty()) {
                return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or empty 'name' query parameter"
                )
            }
            if (page == null || page < 1) {
                return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid or missing 'page' query parameter"
                )
            }

            try {
                val plants = plantService.searchPlantsByName(name, page)
                call.respondText(
                    text = Json.encodeToString(plants),
                    contentType = ContentType.Application.Json
                )
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Error searching for plants: ${e.message}"
                )
            }
        }
    }
}




