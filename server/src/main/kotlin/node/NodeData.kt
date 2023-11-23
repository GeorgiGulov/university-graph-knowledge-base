package node

import property.PropertyData
import java.util.*

data class NodeData(
    override val id: String = UUID.randomUUID().toString(),
    override val label: String,
    override val properties: List<PropertyData>
) : ElementOwner {

    fun toNodeDto() = NodeDto(
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