package com.leafy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantDetail(
    @SerialName("common_names")
    val commonNames: List<String>? = null,
    val taxonomy: Taxonomy? = null,
    val url: String? = null,
    @SerialName("gbif_id")
    val gbifId: Long? = null,
    @SerialName("inaturalist_id")
    val inaturalistId: Long? = null,
    val rank: String? = null,
    val description: Description? = null,
    val image: Image? = null,
    val images: List<Image>? = null,
    @SerialName("edible_parts")
    val edibleParts: List<String>? = null,
    val watering: Watering? = null,
    @SerialName("name_authority")
    val nameAuthority: String? = null,
    @SerialName("propagation_methods")
    val propagationMethods: List<String>? = null,
    @SerialName("best_light_condition")
    val bestLightCondition: String? = null,
    @SerialName("best_soil_type")
    val bestSoilType: String? = null,
    @SerialName("common_uses")
    val commonUses: String? = null,
    @SerialName("cultural_significance")
    val culturalSignificance: String? = null,
    val toxicity: String? = null,
    @SerialName("best_watering")
    val bestWatering: String? = null,
)

@Serializable
data class Taxonomy(
    val `class`: String? = null,
    val genus: String? = null,
    val order: String? = null,
    val family: String? = null,
    val phylum: String? = null,
    val kingdom: String? = null
)

@Serializable
data class Description(
    val value: String? = null,
    val citation: String? = null,
    @SerialName("license_name")
    val licenseName: String? = null,
    @SerialName("license_url")
    val licenseUrl: String? = null
)

@Serializable
data class Image(
    val value: String? = null
)

@Serializable
data class Watering(
    val max: Int? = null,
    val min: Int? = null
)



