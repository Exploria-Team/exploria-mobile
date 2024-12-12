package com.app.exploria.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.request.PreferencesRequest
import com.app.exploria.data.remote.response.Data
import com.app.exploria.data.remote.response.GetPreferenceDataItem
import com.app.exploria.data.remote.response.PreferenceResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    @Named("ApiServiceWithToken") private val apiService: ApiService,
    private val context: Context
) {

    private val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
    private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
    private val MAXIMAL_SIZE = 1000000

    private fun uriToFile(imageUri: Uri): File {
        val myFile = createCustomTempFile(context)
        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(myFile)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return myFile.reduceFileImage()
    }

    private fun createCustomTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            timeStamp,
            ".jpg",
            storageDir
        )
    }

    private fun File.reduceFileImage(): File {
        val bitmap = BitmapFactory.decodeFile(this.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE && compressQuality > 0)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(this))
        return this
    }

    suspend fun getUserData(id: Int): Result<Data> {
        return try {
            val response = apiService.getUserData(id)
            if (response.statusCode == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Failed to fetch user data. Status code: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserData(
        id: Int,
        name: String?,
        email: String?,
        profilePictureUri: Uri?,
        age: String?
    ): Result<Data> {
        return try {
            // Siapkan bagian-bagian multipart
            val namePart = name?.toRequestBody("text/plain".toMediaTypeOrNull())
            val emailPart = email?.toRequestBody("text/plain".toMediaTypeOrNull())
            val age = age?.toRequestBody("text/plain".toMediaTypeOrNull())

            // Siapkan gambar profil jika ada
            val profilePicturePart: MultipartBody.Part? = profilePictureUri?.let { uri ->
                val file = uriToFile(uri)
                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("profilePicture", file.name, requestFile)
            }

            val response = apiService.updateUser(
                id = id, // Kirim ID sebagai Int
                name = namePart,
                email = emailPart,
                age = age,
                profilePicture = profilePicturePart
            )

            if (response.statusCode == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Update failed with status code: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            e.printStackTrace() // Tambahkan logging untuk debugging
            Result.failure(e)
        }
    }

    suspend fun postPreferences(destinationIds: List<Int>): Result<PreferenceResponse> {
        return try {
            val response = apiService.preference(PreferencesRequest(preferences = destinationIds))
            if (response.statusCode == 200) {
                Result.success(response)
            } else {
                Result.failure(Exception("Error posting preferences: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPreference(): Result<List<GetPreferenceDataItem>> {
        return try {
            val response = apiService.getPreferences()
            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Error getting preferences: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
