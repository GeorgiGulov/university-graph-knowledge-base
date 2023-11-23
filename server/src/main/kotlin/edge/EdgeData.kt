package edge

import node.ElementOwner
import node.NodeData
import property.PropertyData
import java.util.*

data class EdgeData(
    override val id: String = UUID.randomUUID().toString(),
    override val label: String,
    override val properties: List<PropertyData>,
    val sourceNode: NodeData,
    val targetNode: NodeData,
) : ElementOwner {

    fun toEdgeDto() = EdgeDto(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyDto() },
        sourceNode = sourceNode.toNodeDto(),
        targetNode = targetNode.toNodeDto()
    )

    override fun toString(): String =
        "\nEdgeData = (" +
                "\n     id = $id," +
                "\n     label = $label," +
                "\n     properties = [$properties]" +
                "\n     sourceNode = [id = ${sourceNode.id}, label = ${sourceNode.label}]," +
                "\n     targetNode = [id = ${targetNode.id}, label = ${targetNode.label}]" +
                "\n)"
}