package node

interface InnerElement {
    val id: String
    val label: String
}

interface ElementValue : InnerElement {
    val value: Any
}

interface ElementOwner : InnerElement {
    val properties: List<ElementValue>
}