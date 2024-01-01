package knowledgegraph.server.entity

import knowledgegraph.server.dto.graph.NodeDataDto
import java.util.*

/**
 * Серверая сущость вершиы графа
 */
data class NodeData(
    override val id: String = UUID.randomUUID().toString(),
    override val label: String,
    override val properties: List<PropertyData>
) : ElementOwner {

    fun toNodeDataDto() = NodeDataDto(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyDto() }
    )

    override fun toString(): String =
        "\nNodeData = (" +
                "\n     id = $id," +
                "\n     label = $label," +
                "\n     properties = [$properties]\n" +
                ")"
}