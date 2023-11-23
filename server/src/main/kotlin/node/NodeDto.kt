package node

import kotlinx.serialization.Serializable
import property.PropertyDto
import java.util.*

@Serializable
data class NodeDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDto>
) {
    fun toNodeData() = NodeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() }
    )
}
