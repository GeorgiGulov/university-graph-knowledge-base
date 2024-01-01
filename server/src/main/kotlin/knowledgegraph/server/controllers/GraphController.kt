package knowledgegraph.server.controllers

import knowledgegraph.server.dto.graph.GraphDataDto
import knowledgegraph.server.dto.query.QueryExecute
import knowledgegraph.server.project.ProjectManager
import knowledgegraph.server.services.GraphService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("graph")
class GraphController {

    @PostMapping("saveGraph")
    fun saveGraph(
        @RequestBody graphDto: GraphDataDto,
    ) {
        val project = ProjectManager.getOrCreateProject(name = "1")
        val newGraph = graphDto.toGraphData()
        GraphService().saveGraph(project, newGraph)

    }

    @PostMapping("executeQuery")
    fun executeQuery(
        @RequestBody queryDto: QueryExecute,
    ): GraphDataDto {
        println(queryDto)
        val project = ProjectManager.getOrCreateProject(name = "1")
        val searchGraph = queryDto.query.toGraphData()

        return GraphService().executeQuery(project, searchGraph).toGraphDto()
    }
}