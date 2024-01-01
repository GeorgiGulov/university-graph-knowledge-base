package knowledgegraph.server.entity

import knowledgegraph.server.dto.graph.PropertyDataDto

data class PropertyData(
    override val label: String,
    override val value: Any,
    override val id: String,
): ElementValue {

    fun toPropertyDto() = PropertyDataDto(
        id = id,
        label = label,
        value = value.toString()
    )
}