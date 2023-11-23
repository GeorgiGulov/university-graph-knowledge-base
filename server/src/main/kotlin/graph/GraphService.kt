package graph

import graph.GraphData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import graph.GraphRepository

class GraphService(g: GraphTraversalSource) {
    private val graphRepository = GraphRepository(g)

    fun saveGraph(newGraph: GraphData) {
        graphRepository.saveGraph(newGraph)
    }

    fun query(queryGraph: GraphData): GraphData = graphRepository.query(queryGraph = queryGraph)
}