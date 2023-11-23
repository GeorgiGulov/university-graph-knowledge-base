package edge

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource

class EdgeService(
    g: GraphTraversalSource
) {
    private val edgeRepository = EdgeRepository(g)

    fun saveEdge(newEdge: EdgeData): String = edgeRepository.saveEdge(newEdge)
}