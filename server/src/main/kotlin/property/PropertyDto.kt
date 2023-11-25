package property

import kotlinx.serialization.Serializable


@Serializable
data class PropertyDto(
    val id: String,
    val label: String,
    val value: String,
) {
    fun toPropertyData() = PropertyData(
        id = id,
        label = label,
        value = value
    )
}
