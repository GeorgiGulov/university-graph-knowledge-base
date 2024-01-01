package knowledgegraph.server.dto.query

import knowledgegraph.server.dto.graph.GraphDataDto

data class QueryExecute(
    val query: GraphDataDto,
    val pageable: Pageable
)