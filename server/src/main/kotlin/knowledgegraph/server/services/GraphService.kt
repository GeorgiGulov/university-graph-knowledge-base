package knowledgegraph.server.services

import knowledgegraph.server.entity.GraphData
import knowledgegraph.server.project.Project
import knowledgegraph.server.repository.GraphRepository

class GraphService {
    fun saveGraph(
        project: Project,
        newGraph: GraphData
    ) = GraphRepository().saveGraph(project, newGraph)

    fun executeQuery(
        project: Project,
        queryGraph: GraphData
    ): GraphData = GraphRepository().executeQuery(project, queryGraph)

}