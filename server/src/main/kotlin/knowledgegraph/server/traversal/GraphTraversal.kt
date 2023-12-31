package knowledgegraph.server.traversal

import knowledgegraph.server.entity.GraphData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Element
import org.apache.tinkerpop.gremlin.structure.Vertex


fun GraphTraversalSource.query(
    queryGraph: GraphData,
): GraphData {
    val nodes = queryGraph.nodes
    val edges = queryGraph.edges

    val listUsedNodeId = edges.flatMap { edge ->
        listOf(edge.sourceNode.id, edge.targetNode.id)
    }.distinct()

    val unusedNode = nodes.filter { it.id !in listUsedNodeId }
    val listElement = mutableListOf<Element>()

    var traversal = this.V()
    var needV = false

    edges.forEachIndexed { index, searchEdge ->
        if (index > 0) traversal = traversal.V()
        traversal = traversal.edge(searchEdge)
        needV = true
    }

    unusedNode.forEachIndexed { index, searchNode ->
        if (index > 0 || needV) {
            traversal = traversal.V()
            needV = true
        }
        traversal = traversal.node(searchNode).`as`(searchNode.id)
    }

    val elements = traversal.path().unfold<Element>().dedup().toSet()
    listElement.addAll(elements)


    return listElement.toGraphData()
}


fun List<Element>.toGraphData(): GraphData {
    val nodes = this.filterIsInstance<Vertex>().map { it.toNodeData() }.distinct()
    val edges = this.filterIsInstance<Edge>().map { it.toEdgeData() }.distinct()
    return GraphData(nodes = nodes, edges = edges)
}