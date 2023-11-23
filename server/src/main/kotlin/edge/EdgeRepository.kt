package edge

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource

class EdgeRepository(
    val g: GraphTraversalSource
) {
    fun saveEdge(newEdge: EdgeData): String = g.addEdge(newEdge)
}