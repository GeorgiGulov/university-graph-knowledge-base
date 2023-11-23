package edge

import node.ElementValue
import node.node
import property.PropertyData
import node.toNodeData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.`__` as tmp
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Vertex


fun GraphTraversalSource.addEdge(
    newEdge: EdgeData
): String {
    val source = this.V(newEdge.sourceNode.id.toLong()).next()
    val target = this.V(newEdge.targetNode.id.toLong()).next()

    return this.addE(newEdge.label)
        .from(source)
        .to(target)
        .property(newEdge.properties.associate { it.label to it.value })
        .next().id().toString()

}


fun GraphTraversal<Vertex, Vertex>.edge(
    searchEdge: EdgeData
): GraphTraversal<Vertex, Vertex> =
    this
        .node(searchEdge.sourceNode)
        .`as`(searchEdge.sourceNode.id)
        .select<Vertex>(searchEdge.sourceNode.id)
        .outE()
        .hasLabel(searchEdge.label)
        .properties(searchEdge.properties)
        .inV()
        .node(searchEdge.targetNode)
        .`as`(searchEdge.targetNode.id)


fun GraphTraversal<Vertex, Edge>.properties(properties: List<ElementValue>) = this.apply {
    properties.forEach {
        this.has(it.label, it.value)
    }
}


fun Edge.toEdgeData(): EdgeData = EdgeData(
    id = this.id().toString(),
    label = this.label().toString(),
    sourceNode = this.outVertex().toNodeData(),
    targetNode = this.inVertex().toNodeData(),
    properties = this.getProperty()
)


fun Edge.getProperty(): List<PropertyData> =
    this.properties<Any>().asSequence().map { property ->
        PropertyData(
            id = "property-edge-${this.id()}",
            label = property.key(),
            value = property.value()
        )
    }.toList()