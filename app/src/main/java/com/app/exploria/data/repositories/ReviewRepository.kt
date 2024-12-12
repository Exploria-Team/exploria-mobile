package com.app.exploria.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.exploria.data.remote.api.ApiService
import com.app.exploria.data.remote.pagingSource.ReviewPagingSource
import com.app.exploria.data.remote.response.PostReviewData
import com.app.exploria.data.remote.response.ReviewsItem
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
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

    fun getReviews(id: Int, pageSize: Int = 3): Flow<PagingData<ReviewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                maxSize = 20
            ),
            pagingSourceFactory = { ReviewPagingSource(apiService, id, pageSize) }
        ).flow
    }

    suspend fun submitReview(
        destinationId: Int,
        reviewText: String,
        rating: Int,
        reviewPhotoUri: Uri?
    ): Result<PostReviewData> {
        return try {
            val destinationIdBody = destinationId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val reviewTextBody = reviewText.toRequestBody("text/plain".toMediaTypeOrNull())
            val ratingBody = rating.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            val photoPart: MultipartBody.Part? = reviewPhotoUri?.let { uri ->
                val file = uriToFile(uri)
                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("reviewPhoto", file.name, requestFile)
            }

            val response = apiService.submitReview(
                destinationId = destinationIdBody,
                reviewText = reviewTextBody,
                rating = ratingBody,
                photo = photoPart
            )

            if (response.statusCode == 200) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("Can't submit a Review: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
