package com.gurihouses.postproperty.fragments

import android.Manifest
import android.Manifest.permission.CAMERA
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gurihouses.databinding.FragmentPostPropertyBinding
import com.gurihouses.home.tabfragment.viewmodel.HomeTabViewModel
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar
import java.util.ArrayList

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gurihouses.R
import com.gurihouses.network.ApiConstants
import com.gurihouses.network.MultiPartHelperClass
import com.gurihouses.postproperty.viewmodel.PostPropertyViewModel
import com.gurihouses.util.CommonUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import okhttp3.MultipartBody

import okhttp3.MediaType.Companion.toMediaTypeOrNull

import okhttp3.RequestBody
import org.w3c.dom.Text

import java.io.File
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.net.toUri
import com.gurihouses.util.FileUtils
import java.lang.Exception






/**
 * A simple [Fragment] subclass.
 * Use the [PostPropertyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostPropertyFragment : Fragment(),View.OnClickListener {

    private val REQUEST_IMAGE_CAPTURE = 1
    private val PICK_IMAGE_MULTIPLE = 2
    lateinit var binding: FragmentPostPropertyBinding
    private val mTabViewModel: HomeTabViewModel by viewModels()
    private val mPostViewModel: PostPropertyViewModel by viewModels()
    val mListingType = ArrayList<String>()
    private lateinit var sessionManager: SessionManager
//    lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
    var state_id = "0"
    var lat = "0.0"
    var lng = "0.0"
    var location = ""
    var state = ""
    var city = ""
    var mlistingType = "0"
    var mPropertyType = "0"
    var mCategory = "0"
    var parts: MutableList<MultipartBody.Part> = ArrayList<MultipartBody.Part>()
    var imagelist = arrayListOf<String>()
    var currentPhotoPath: String? = ""

    companion object {

        fun newInstance() =
            PostPropertyFragment().apply {
                arguments = Bundle().apply {


                }
            }
    }



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
            binding = FragmentPostPropertyBinding.inflate(layoutInflater)
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        myActivityResultLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) {
//            if (it.resultCode == AppCompatActivity.RESULT_OK) {
//                val selectedFileUri: Uri? = it.data?.data
//                if (selectedFileUri != null) {
//
//                    val file = File(selectedFileUri.path.toString())
//                    Log.d("imagerequest",file.absolutePath)
//                    imagelist.add(file.absolutePath)
//                   //imagelist.add(selectedFileUri.toString())
//
//                }
//
//            }
//        }


        initialization()
        loadListType()
        listener()

        getCrteatePostViewModel()


    }

    private fun getCrteatePostViewModel() {

        mPostViewModel.mPostResponse?.observe(viewLifecycleOwner, Observer {

            if (it !=null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    CommonUtil.showMessage(requireContext(),message.toString())
                }
            }

        })

        mPostViewModel.errorMsg?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                Log.d("error",it.toString())
                CommonUtil.showMessage(requireContext(), it.toString())

            }

        })

        mPostViewModel.loadingStatus?.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })

    }

    private fun listener() {

        binding.rlUpload.setOnClickListener(this)
        binding.btnCreatePost.setOnClickListener(this)



    }

    private fun setViewListingType(listType: ArrayList<String>) {

        val mlistingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
            listType)
        mlistingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerListType.adapter = mlistingAdapter
        binding.spinnerListType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                mlistingType = position.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        val list = arrayListOf<String>()
        list.add("Select property type")
        list.add("Land")
        list.add("House")
        val mPropertyAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
            list
        )
        mPropertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerListType2.adapter = mPropertyAdapter
        binding.spinnerListType2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                mPropertyType = position.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        val list1 = arrayListOf<String>()
        list1.add("Select property category")
        list1.add("Land")
        list1.add("House")
        val mCategoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
            list1
        )
        mCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerListType3.adapter = mCategoryAdapter
        binding.spinnerListType3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                  mCategory = position.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }

    private fun initialization() {
        sessionManager = SessionManager(requireContext())
        state_id = sessionManager.getStateSession()[SessionVar.STATE_ID].toString()
        lat = sessionManager.getLatitudeSession()[SessionVar.LATITUDE].toString()
        lng = sessionManager.getLongitudeSession()[SessionVar.LONGITUDE].toString()
        location = sessionManager.getCitySession()[SessionVar.OWNER_CITY].toString()
        state = sessionManager.getStatSession()[SessionVar.OWNER_STATE].toString()
        city = sessionManager.getStatSession()[SessionVar.OWNER_CITY].toString()
        binding.editPropertyLoc.setText(location)




    }

    private fun loadListType() {

        mTabViewModel.getHomeTabDetails(state_id.toInt())
        mTabViewModel.HomeTabResponse?.observe(viewLifecycleOwner) {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    response.data.forEach {
                        mListingType.add(it.name)
                    }

                    mListingType.add(0, "Select listing type")
                    setViewListingType(mListingType)
                }
            }

        }

        mTabViewModel.loadingStatus?.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.rl_upload->{
                getGalleryPermission()
            }

            R.id.btn_create_post->{

                validatePostField()
            }

        }
    }

    private fun validatePostField() {

        val contributorsMap: HashMap<String, RequestBody> = HashMap()

        // Multiple Images
        for (i in 0 until imagelist.size) {
            val file2 = File(imagelist[i])
            val surveyBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file2)

            parts.add(i,MultipartBody.Part.createFormData("image", file2.path, surveyBody))

        }

        contributorsMap["owner_id"] =  MultiPartHelperClass.getRequestBody("2")
        contributorsMap["title"] =  MultiPartHelperClass.getRequestBody(binding.editPostTitle.text.toString())
        contributorsMap["location"] = MultiPartHelperClass.getRequestBody(binding.editPropertyLoc.text.toString())
        contributorsMap["lat"] =    MultiPartHelperClass.getRequestBody(lat)
        contributorsMap["lng"] =    MultiPartHelperClass.getRequestBody(lng)
        contributorsMap["price"] =  MultiPartHelperClass.getRequestBody(binding.editPropertyPrice.text.toString())
        contributorsMap["listing_type"] = MultiPartHelperClass.getRequestBody(mlistingType)
        contributorsMap["property_type"] = MultiPartHelperClass.getRequestBody(mPropertyType)
        contributorsMap["state"] =  MultiPartHelperClass.getRequestBody(state)
        contributorsMap["city"] =  MultiPartHelperClass.getRequestBody("delhi")
        contributorsMap["des"] = MultiPartHelperClass.getRequestBody(binding.editPostDescription.text.toString())
        contributorsMap["rooms"] =  MultiPartHelperClass.getRequestBody("2")
        contributorsMap["area"] =   MultiPartHelperClass.getRequestBody("883")
        contributorsMap["balcony"] =   MultiPartHelperClass.getRequestBody("2")
        contributorsMap["face"] =   MultiPartHelperClass.getRequestBody("east")
        contributorsMap["Apikey"] =   MultiPartHelperClass.getRequestBody(ApiConstants.API_KEY)

        Log.d("gurimap",contributorsMap.toString())

        mPostViewModel.getPostProperty(contributorsMap, parts)



    }



    private fun getGalleryPermission() {

        Dexter.withContext(context).withPermissions(

            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE

        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                openDialogToUpdateProfilePIC()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }

        }).check()
    }

//    private fun chooseImageGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        myActivityResultLauncher.launch(intent)
//    }


    private fun openDialogToUpdateProfilePIC() {

        val btnsheet = layoutInflater.inflate(R.layout.image_bottomsheet_layout, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(btnsheet)
        dialog.findViewById<TextView>(R.id.tv_select_camera)?.setOnClickListener {
            launchCameraIntent()
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.tv_select_gallry)?.setOnClickListener {
            launchGalleryIntent()
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.tv_cancel)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun launchCameraIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            context?.let {
                intent.resolveActivity(it.packageManager)?.also {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

    }

    private fun launchGalleryIntent() {

        val intent = Intent()
        intent.type = "image/*"
        // allowing multiple image to be selected
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                CommonUtil.showMessage(requireContext(),bitmap.toString())

            } else if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {

                try {
                    if (data.clipData != null) {
                        val count = data.clipData!!.itemCount
                        for (i in 0 until count) {
                            val imageUri: Uri = data.clipData!!.getItemAt(i).uri

                            val mupload = FileUtils.getRealPathFromURI(requireContext(),imageUri)
                            val file = File(imageUri.path)
                            currentPhotoPath = mupload

                            val imagePath = FileUtils.getFilePath(requireContext(), imageUri)
                            Log.e("imageUri", currentPhotoPath.toString())
                            imagelist.add(currentPhotoPath.toString())
                            Log.e("imagePath", imagelist.toString())
                        }
                    } else if (data.data != null) {
                        val imagePath:Uri = data.data!!
                        val mupload = FileUtils.getRealPathFromURI(requireContext(),imagePath)

                        currentPhotoPath = mupload
                        Log.e("imagePath", currentPhotoPath.toString())
                        imagelist.add(currentPhotoPath.toString())
                    }

                }catch (e:Exception){
                    e.printStackTrace()
            }


            }
        }
    }

}