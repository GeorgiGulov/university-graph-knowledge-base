package edge

import kotlinx.serialization.Serializable
import node.NodeDto
import property.PropertyDto
import java.util.*

@Serializable
data class EdgeDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDto>,
    val sourceNode: NodeDto,
    val targetNode: NodeDto,
) {
    fun toEdgeData() = EdgeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() },
        sourceNode = sourceNode.toNodeData(),
        targetNode = targetNode.toNodeData()
    )

}
