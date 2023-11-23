package photo

import java.io.File

class PhotoService(
    private val photoRepository: PhotoRepository = PhotoRepository()
) {
    fun savePhoto(photo: File): String = photoRepository.savePhoto(photo = photo)

    fun getPhoto(uuidName: String): File? = photoRepository.getPhoto(uuidName = uuidName)

}