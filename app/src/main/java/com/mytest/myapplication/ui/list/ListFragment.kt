package com.mytest.myapplication.ui.list

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mytest.myapplication.R
import com.mytest.myapplication.databinding.FragmentListBinding
import com.mytest.myapplication.network.model.RepoInfo
import com.mytest.myapplication.ui.Adapter
import com.mytest.myapplication.ui.RepoViewModel

class ListFragment : Fragment(), Adapter.ClickEvents {


    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: Adapter
    private val viewModel: RepoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        subscribeUi()
        subscribeObservers()


        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(){
        reload()
        adapter = Adapter(this)
        binding.recyclerView.adapter = adapter
    }


    private fun reload(){
        val sharedPref: SharedPreferences = activity?.getSharedPreferences(
            "SharedPrefs",
            Context.MODE_PRIVATE
        )!!
        val sort = sharedPref.getString("sortBy", "")
        if (sort != null) {
            viewModel.getTheRepo(sort)
        } else {
            viewModel.getTheRepo("weekly")
        }
    }

    private fun subscribeObservers(){
        viewModel.repoLive.observe(viewLifecycleOwner, { response ->
            if (response != null) {
                val items: List<RepoInfo> = response
                adapter.setNewList(items)

                //  Log.i("meow", items.toString())
                //binding.progressBar.visibility = View.GONE
            } else {
                Toast.makeText(requireContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show()
                //binding.progressBar.visibility = View.GONE
            }
        })
    }



    private fun showDialog() {
        val sharedPref: SharedPreferences =
            activity?.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)!!
        val editor: SharedPreferences.Editor = sharedPref.edit()
        lateinit var dialog: AlertDialog

        val array = arrayOf("daily", "weekly", "monthly")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Sort by:")
        builder.setSingleChoiceItems(array, -1) { _, which ->
            val choice = array[which]
            editor.putString("sortBy", choice).apply()
            dialog.dismiss()
            reload()
        }
        builder.setNeutralButton("Cancel") { _, _ ->
            dialog.cancel()
        }
        dialog = builder.create()
        dialog.show()




    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btSort) {
            showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewClicked(repo: RepoInfo, view: View) {
        Toast.makeText(requireContext(), repo.author, Toast.LENGTH_SHORT).show()

//        val navController = findNavController()
//        val action = ListFragmentDirections.actionListFragmentToWebFragment(repo)
//
//
//        if (navController.currentDestination?.id == R.id.webFragment) {
//            navController.navigate(action)
//        }
    }


}