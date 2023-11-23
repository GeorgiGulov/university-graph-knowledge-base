package plugins

import getLogger
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.DriverManager

fun Application.initializeDataBase() {
    val logger = getLogger()

    val jdbcURL = "jdbc:postgresql://localhost:5432"
    val databaseName = "stolovay-bot"
    val userName ="postgres"
    val password = "postgres"

    Database.connect(
        getNewConnection = {
            DriverManager.getConnection(
                "$jdbcURL/$databaseName",
                userName,
                password
            )
        }
    )

    createTables()
    logger.info("Initialized Database")

}


private fun createTables() = transaction {
//    SchemaUtils.create()
}