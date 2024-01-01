package knowledgegraph.server.repository

import knowledgegraph.server.entity.GraphData
import knowledgegraph.server.project.Project
import knowledgegraph.server.traversal.addEdge
import knowledgegraph.server.traversal.addNode
import knowledgegraph.server.traversal.query
import knowledgegraph.server.utils.getLogger

class GraphRepository {
    private val logger = getLogger()

    fun saveGraph(
        project: Project,
        newGraph: GraphData
    ) {
        val traversal = project.connection.traversal

        val mapNode = newGraph.nodes.associateWith { node ->
            traversal.addNode(newNode = node)
        }

        newGraph.edges.forEach { newEdge ->
            val newSourceId = mapNode[newEdge.sourceNode]
            val newTargetId = mapNode[newEdge.sourceNode]

            if (newSourceId != null && newTargetId != null) {
                val edge = newEdge.copy(
                    sourceNode = newEdge.sourceNode.copy(id = newSourceId),
                    targetNode = newEdge.targetNode.copy(id = newTargetId)
                )

                traversal.addEdge(edge)
            } else {
                logger.warn("Failed to save edge due to problems with vertices")
            }
        }
    }

    fun executeQuery(
        project: Project,
        queryGraph: GraphData
    ): GraphData =
        project.connection.traversal.query(queryGraph = queryGraph)

}