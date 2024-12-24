package com.leafy.extensions

import com.leafy.*
import java.sql.ResultSet

fun ResultSet.toPlantDetail(): PlantDetail {
    return PlantDetail(
        commonNames = getString("common_names")?.split(",")?.map { it.trim() },
        taxonomy = Taxonomy(
            `class` = getString("taxonomy_class"),
            genus = getString("taxonomy_genus"),
            order = getString("taxonomy_order"),
            family = getString("taxonomy_family"),
            phylum = getString("taxonomy_phylum"),
            kingdom = getString("taxonomy_kingdom")
        ),
        url = getString("url"),
        gbifId = getLongOrNull("gbif_id"),
        inaturalistId = getLongOrNull("inaturalist_id"),
        rank = getString("rank"),
        description = Description(
            value = getString("description"),
            citation = getString("description_citation"),
            licenseName = getString("description_license_name"),
            licenseUrl = getString("description_license_url")
        ),
        image = Image(
            value = getString("image_url")
        ),
        images = getString("images_urls")?.split(",")?.map { Image(it.trim()) },
        edibleParts = getString("edible_parts")?.split(",")?.map { it.trim() },
        watering = Watering(
            max = getIntOrNull("watering_max"),
            min = getIntOrNull("watering_min")
        ),
        nameAuthority = getString("name_authority"),
        propagationMethods = getString("propagation_methods")?.split(",")?.map { it.trim() },
        bestLightCondition = getString("best_light_condition"),
        bestSoilType = getString("best_soil_type"),
        commonUses = getString("common_uses"),
        culturalSignificance = getString("cultural_significance"),
        toxicity = getString("toxicity"),
        bestWatering = getString("best_watering")
    )
}

fun ResultSet.getIntOrNull(columnLabel: String): Int? {
    val value = getInt(columnLabel)
    return if (wasNull()) null else value
}

fun ResultSet.getLongOrNull(columnLabel: String): Long? {
    val value = getLong(columnLabel)
    return if (wasNull()) null else value
}

fun ResultSet.getStringOrNull(columnLabel: String): String? {
    return getString(columnLabel)?.takeIf { !wasNull() }
}