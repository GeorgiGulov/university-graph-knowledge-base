package node

import node.NodeData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import node.NodeRepository

class NodeService(
    g: GraphTraversalSource
) {
    private val nodeRepository = NodeRepository(g)

    fun saveNode(newNode: NodeData): String = nodeRepository.saveNode(newNode)
}