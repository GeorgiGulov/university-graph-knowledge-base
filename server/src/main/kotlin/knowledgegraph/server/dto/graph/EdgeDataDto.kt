package knowledgegraph.server.dto.graph

import knowledgegraph.server.entity.EdgeData
import java.util.*

data class EdgeDataDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDataDto>,
    val sourceNode: NodeDataDto,
    val targetNode: NodeDataDto,
) {
    fun toEdgeData() = EdgeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() },
        sourceNode = sourceNode.toNodeData(),
        targetNode = targetNode.toNodeData()
    )

}