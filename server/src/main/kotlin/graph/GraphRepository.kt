package graph

import edge.EdgeRepository
import node.NodeRepository
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource

class GraphRepository(
    private val g: GraphTraversalSource
) {
    private val nodeRepository = NodeRepository(g)
    private val edgeRepository = EdgeRepository(g)

    fun saveGraph(newGraph: GraphData) {
        val mapNode = newGraph.nodes.associateWith { node ->
            nodeRepository.saveNode(node)
        }

        newGraph.edges.forEach { newEdge ->
            val edge = newEdge.copy(
                sourceNode = newEdge.sourceNode.copy(id = mapNode[newEdge.sourceNode] ?: "-1"),
                targetNode = newEdge.targetNode.copy(id = mapNode[newEdge.targetNode] ?: "-1")
            )
            edgeRepository.saveEdge(edge)
        }

    }

    fun query(queryGraph: GraphData): GraphData = query(queryGraph = queryGraph, g = g)

}