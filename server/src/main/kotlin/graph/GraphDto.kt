package graph

import edge.EdgeDto
import kotlinx.serialization.Serializable
import node.NodeDto

@Serializable
data class GraphDto(
    val nodes: List<NodeDto>,
    val edges: List<EdgeDto>
) {
    fun toGraphData() = GraphData(
        nodes = nodes.map { it.toNodeData() },
        edges = edges.map { it.toEdgeData() }
    )
}
