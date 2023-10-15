package com.theyoseph.byteswtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

private lateinit var adapter: PostAdapter
private lateinit var recycler: RecyclerView
private lateinit var list: MutableList<ResponseAPIElement>

class MasterFragment : Fragment() {
    private var list: MutableList<ResponseAPIElement>? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getSerializable("info") as? MutableList<ResponseAPIElement>
            //param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recycler = view.findViewById(R.id.recyclerMaster)
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(true)
        adapter = list?.let { PostAdapter(it) }!!
        recycler.adapter = adapter
    }

    companion object {
        fun newInstance(list: MutableList<ResponseAPIElement>): MasterFragment{
            val fragment = MasterFragment()
            val args = Bundle()
            args.putSerializable("info", list as Serializable)
            fragment.arguments = args
            return fragment
        }
    }
}