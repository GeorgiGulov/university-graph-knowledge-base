package property

import kotlinx.serialization.Serializable


@Serializable
data class PropertyDto(
     val label: String,
     val value: String,
) {
    fun toPropertyData() = PropertyData(
        label = label,
        value = value
    )
}
