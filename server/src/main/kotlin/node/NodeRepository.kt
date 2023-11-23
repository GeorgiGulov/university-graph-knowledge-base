package node

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource

class NodeRepository(
    private val g: GraphTraversalSource
) {
    fun saveNode(newNode: NodeData): String = g.addNode(newNode)

}