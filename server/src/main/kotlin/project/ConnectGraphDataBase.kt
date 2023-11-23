package project

import getLogger
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

class ConnectGraphDataBase(
    val nameProject: String
) {
    private val logger = getLogger()

    val graph: Graph by lazy {
        logger.info("Open connection with graph database for Project = [$nameProject]")
        TinkerGraph.open()
    }

    val traversal: GraphTraversalSource = AnonymousTraversalSource.traversal().withEmbedded(graph)
}