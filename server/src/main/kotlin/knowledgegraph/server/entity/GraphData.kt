package knowledgegraph.server.entity

import knowledgegraph.server.dto.graph.GraphDataDto

data class GraphData(
    val nodes: List<NodeData> = listOf(),
    val edges: List<EdgeData> = listOf(),
) {
    fun toGraphDto() = GraphDataDto(
        nodes = nodes.map { it.toNodeDataDto() },
        edges = edges.map { it.toEdgeDataDto() }
    )
}