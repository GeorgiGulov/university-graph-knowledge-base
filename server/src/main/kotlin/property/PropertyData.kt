package property

import node.ElementValue

data class PropertyData(
    override val label: String,
    override val value: Any,
    override val id: String,
): ElementValue {

    fun toPropertyDto() = PropertyDto(
        id = id,
        label = label,
        value = value.toString()
    )
}
