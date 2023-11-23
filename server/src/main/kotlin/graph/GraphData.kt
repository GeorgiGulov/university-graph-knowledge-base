package graph

import edge.EdgeData
import node.NodeData

data class GraphData(
    val nodes: List<NodeData> = listOf(),
    val edges: List<EdgeData> = listOf(),
) {
    fun toGraphDto() = GraphDto(
        nodes = nodes.map { it.toNodeDto() },
        edges = edges.map { it.toEdgeDto() }
    )
}