package photo

import java.io.File

class PhotoRepository() {
    private val photoUploader = PhotoUploader()

    fun savePhoto(photo: File): String = photoUploader.uploadPhoto(photo = photo)

    fun getPhoto(uuidName: String): File? = photoUploader.getPhotoByUUID(uuid = uuidName)

}