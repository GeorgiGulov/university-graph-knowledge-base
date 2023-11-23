package photo

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.photoRouting() {
    val photoService: PhotoService = PhotoService()

    routing {
        post("/savePhoto") {
            val multipart = call.receiveMultipart()

            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val fileName = part.originalFileName ?: "unknown"
                    val fileBytes = part.streamProvider().readBytes()

                    val file = File(fileName)
                    file.writeBytes(fileBytes)
                    photoService.savePhoto(file)

                }
                part.dispose()
            }

            call.respond(HttpStatusCode.OK)
        }
    }

}