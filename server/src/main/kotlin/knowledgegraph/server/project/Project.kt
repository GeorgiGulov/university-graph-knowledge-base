package knowledgegraph.server.project

data class Project(val name: String) {
    val connection: ConnectGraph by lazy { ConnectGraph(nameProject = name) }
}