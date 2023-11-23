package project

import graph.GraphService

data class Project(val name: String) {
    private val connection: ConnectGraphDataBase by lazy { ConnectGraphDataBase(nameProject = name) }

    val graphService: GraphService by lazy { GraphService(connection.traversal) }

}
