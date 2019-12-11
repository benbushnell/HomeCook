package hu.ait.androidfinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import hu.ait.androidfinal.R
import hu.ait.androidfinal.adapter.PantryAdapter
import hu.ait.androidfinal.dialogs.NewPantryItemDialog
import kotlinx.android.synthetic.main.pantry_fragment.*

class PantryFragment : Fragment() {

    companion object {
        const val TAG_ITEM_DIALOG = "TAG_ITEM_DIALOG"
    }

    private lateinit var viewModel: RecipeViewModel
    lateinit var pantryAdapter: PantryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pantry_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
        pantryAdapter = PantryAdapter(activity!!)
        recyclerPantry.adapter = pantryAdapter
        recyclerPantry.layoutManager = LinearLayoutManager(activity)


        btnAddPantryItem.setOnClickListener {
            NewPantryItemDialog().show(activity!!.supportFragmentManager, TAG_ITEM_DIALOG)

        }
    }
}