package com.leafy.db

import com.leafy.PlantDetail
import com.leafy.extensions.toPlantDetail
import java.sql.Connection

class PlantService(private val connection: Connection){

    fun fetchPlantsByPageSortedById(
        page: Int
    ): List<PlantDetail> {
        val query = """
        SELECT * 
        FROM plantdetail
        ORDER BY id ASC
        LIMIT ? OFFSET ?
    """.trimIndent()
        val pageSize = 20
        val plants = mutableListOf<PlantDetail>()
        connection.prepareStatement(query).use { statement ->
            statement.setInt(1, pageSize)
            statement.setInt(2, (page - 1) * pageSize)

            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                plants.add(resultSet.toPlantDetail())
            }
        }
        return plants
    }

    fun searchPlantsByName(name: String, page: Int): List<PlantDetail> {
        val pageSize = 20
        val query = """
        SELECT * 
        FROM plantdetail
        WHERE LOWER(common_names) LIKE LOWER(? || '%') 
           OR LOWER(common_names) LIKE LOWER('%' || ? || '%') 
        ORDER BY 
           CASE 
               WHEN LOWER(common_names) LIKE LOWER(? || '%') THEN 1
               ELSE 2
           END ASC
        LIMIT ? OFFSET ?
    """.trimIndent()

        val plants = mutableListOf<PlantDetail>()
        connection.prepareStatement(query).use { statement ->
            statement.setString(1, name)
            statement.setString(2, name)
            statement.setString(3, name)
            statement.setInt(4, pageSize)
            statement.setInt(5, (page - 1) * pageSize)

            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                plants.add(resultSet.toPlantDetail())
            }
        }
        return plants
    }
}