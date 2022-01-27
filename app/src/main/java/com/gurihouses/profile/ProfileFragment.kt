package com.gurihouses.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gurihouses.R
import com.gurihouses.about.ui.activities.AboutUsActivity
import com.gurihouses.contactus.ui.activities.ContactUsActivity
import com.gurihouses.databinding.FragmentProfileBinding
import com.gurihouses.faq.ui.activities.FaqActivity
import com.gurihouses.privacypolicy.ui.activities.PrivacyPolicyActivity
import com.gurihouses.myprofile.ui.viewmodels.ProfileViewModel
import com.gurihouses.termscondition.ui.activities.TermsConditionActivity
import com.gurihouses.util.CommonUtil


class ProfileFragment : Fragment(),View.OnClickListener {

    lateinit var binding: FragmentProfileBinding
    private val mViewModel: ProfileViewModel by viewModels()


    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        listener()

    }

    private fun initialization() {

    }

    private fun listener() {

        binding.about.setOnClickListener(this)
        binding.contact.setOnClickListener(this)
        binding.terms.setOnClickListener(this)
        binding.privacy.setOnClickListener(this)
        binding.faq.setOnClickListener(this)
        binding.logout.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.about->{

                val mScreen = Intent(context, AboutUsActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mScreen)
                activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

            R.id.contact->{

                val mScreen = Intent(context, ContactUsActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mScreen)
                activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

            R.id.terms->{

                val mScreen = Intent(context, TermsConditionActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mScreen)
                activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

            R.id.privacy->{

                val mScreen = Intent(context, PrivacyPolicyActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mScreen)
                activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

            R.id.faq->{

                val mScreen = Intent(context, FaqActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(mScreen)
                activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

            R.id.logout->{

              Toast.makeText(activity,"Logout not implemented yet",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun getProfileViewModel(){

        mViewModel.profileResponse?.observe(this,{

            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    activity?.let { it1 -> CommonUtil.showMessage(it1, response.message) }
                    binding.name.setText(response.data.fname)

                }

            }

        })
        mViewModel.errorMsg?.observe(this, Observer {
            if (it != null) {

                activity?.let { it1 -> CommonUtil.showMessage(it1, it.toString()) }

            }

        })

        mViewModel.loadingStatus?.observe(this, Observer {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })


    }

    private fun getProfileApi(){
    mViewModel.getProfile("10")
    }

}