import photo.PhotoUploader
import java.io.File
import kotlin.test.Test
import kotlin.test.assertNotNull


class PhotoUploaderTest {

    private val photoUploader = PhotoUploader()

    @Test
    fun `save and get photo`() {

        val photo1 = File(".//src//test//resources//uploads//work.jpg")
        val photo2 = File(".//src//test//resources//uploads//worker.png")

        val name1 = photoUploader.uploadPhoto(photo1)
        val name2 = photoUploader.uploadPhoto(photo2)

        val photoGet1 = photoUploader.getPhotoByUUID(name1)
        val photoGet2 = photoUploader.getPhotoByUUID(name2)

        assertNotNull(photoGet1)
        assertNotNull(photoGet2)

    }

}