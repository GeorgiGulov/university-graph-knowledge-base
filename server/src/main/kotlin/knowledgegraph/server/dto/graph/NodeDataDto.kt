package knowledgegraph.server.dto.graph

import knowledgegraph.server.entity.NodeData
import java.util.*

data class NodeDataDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDataDto>
) {
    fun toNodeData() = NodeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() }
    )
}