package routing

import edge.EdgeData
import graph.GraphData
import graph.GraphDto
import graph.GraphService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import node.NodeData
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import project.ProjectRepository
import property.PropertyData


@Serializable
data class Pageable(
    val numberPage: Int,
    val size: Int
)

@Serializable
data class QueryExecute(
    val query: GraphDto,
    val pageable: Pageable
)

fun Application.graphRouting() {
    val project = ProjectRepository.createProject("1")

    routing {

        post("/saveGraph") {
            val newGraph = call.receive<GraphDto>()

            project.graphService.saveGraph(newGraph.toGraphData())

            println(newGraph.toGraphData())
            call.respond(HttpStatusCode.OK)

        }


        post("/execute") {

            val queryDto = call.receive<QueryExecute>().query

            val searchGraph = queryDto.toGraphData()

            val graph = project.graphService.query(searchGraph)

            call.respond(HttpStatusCode.OK, graph.toGraphDto())

        }


    }
}