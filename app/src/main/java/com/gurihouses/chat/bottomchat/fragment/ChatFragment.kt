package com.gurihouses.chat.bottomchat.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.R
import com.gurihouses.chat.bottomchat.bean.ChatDataResponse
import com.gurihouses.chat.bottomchat.chatadapter.ChatAdapter
import com.gurihouses.chat.bottomchat.ui.ChatActivity
import com.gurihouses.databinding.FragmentChatBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding

    companion object {

        @JvmStatic
        fun newInstance() =
            ChatFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        listener()

    }

    private fun initialization() {
        binding.inclToolbar.ttitle.text = "Chats"
        loadDummyData()

    }

    private fun loadDummyData() {

        val list = arrayListOf<ChatDataResponse>()
        list.add(
            ChatDataResponse("David",
            "This is a text description for the chat screen and this",
            R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )
        list.add(
            ChatDataResponse("David",
                "This is a text description for the chat screen and this",
                R.drawable.ic_profile_image,"10 October 2021 at 04 .00 PM")
        )

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = ChatAdapter(context, list){

            val chatScreen = Intent(context, ChatActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(chatScreen)
            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }


    }

    private fun listener() {
    }


}