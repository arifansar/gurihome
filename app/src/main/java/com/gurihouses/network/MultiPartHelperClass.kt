package com.gurihouses.network

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList






class MultiPartHelperClass {

    companion object{
        fun getRequestBody(str :String?) : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(),
            str.toString()
        )

        fun getRequestFile(image: ArrayList<String>, key: String): List<MultipartBody.Part> {
            val body: ArrayList<MultipartBody.Part> = arrayListOf()
            for (index in 0 until image.size) {

                Log.d("Upload request", "requestUploadSurvey: survey image " + index + "  " + image[index])
                val file2 = File(image[index])
                val surveyBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file2)

                body.add(MultipartBody.Part.createFormData(key, file2.path, surveyBody))


            }

//            var body: MultipartBody.Part? = null
//            if (jsonFile.isNotEmpty()) {
//                val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), jsonFile.toString())
//                // MultipartBody.Part is used to send also the actual file name
//                body = MultipartBody.Part.createFormData(key, getTimeinMillis().toString() + "_media.jpg", requestFile)
//            }


            return body
            // RequestBody requestFile = null;
            // if (imageUri != null){
            //   File file = new File(imageUri.getPath());
            // requestFile = RequestBody.create(MediaType.parse("​*/*​"), imageFile);
            //}
            //  return requestFile;
        }

        fun getMultipartData(
            imageFile: File,
            key: String
        ): MultipartBody.Part? {
            var body: MultipartBody.Part? = null
            if (imageFile.exists()) {
                val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
                // MultipartBody.Part is used to send also the actual file name
                body = MultipartBody.Part.createFormData(key, getTimeinMillis().toString() + "_media.jpg", requestFile)
            }


            return body
        }

        fun getRequestBody1(imageFile: File, key: String): RequestBody? {
            var body: RequestBody? = null
            if (imageFile.exists()) {
                body = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
                // MultipartBody.Part is used to send also the actual file name
            }
            return body
        }

        fun getTimeinMillis(): Long {
            val cal = Calendar.getInstance()
            cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND)
            )
            return cal.timeInMillis
        }
    }


}