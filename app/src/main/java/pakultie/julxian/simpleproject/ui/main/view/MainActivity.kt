package pakultie.julxian.simpleproject.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pakultie.julxian.simpleproject.R
import pakultie.julxian.simpleproject.data.api.ApiHelper
import pakultie.julxian.simpleproject.data.api.RetrofitBuilder
import pakultie.julxian.simpleproject.data.model.SampleItem
import pakultie.julxian.simpleproject.ui.base.ViewModelFactory
import pakultie.julxian.simpleproject.ui.main.adapter.RecyclerAdapter
import pakultie.julxian.simpleproject.ui.main.adapter.RecyclerItemClickListener
import pakultie.julxian.simpleproject.ui.main.viewmodel.MainViewModel
import pakultie.julxian.simpleproject.utils.AppDatabase
import pakultie.julxian.simpleproject.utils.Character
import pakultie.julxian.simpleproject.utils.Status
import java.text.DecimalFormat
import android.content.Intent as Intent


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()


    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recycler_items.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(arrayListOf())
        recycler_items.addItemDecoration(
            DividerItemDecoration(
                recycler_items.context,
                (recycler_items.layoutManager as LinearLayoutManager).orientation
            )
        )
        recycler_items.adapter = adapter


        recycler_items.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                recycler_items,
                object : RecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        val df = DecimalFormat("#.##")
                        val intent = Intent(this@MainActivity, MainActivityDetail::class.java)
                        intent.putExtra("Index", position.toString())
                        startActivity(intent)
                    }
                    override fun onItemLongClick(view: View?, position: Int) {
                        TODO("do nothing")
                    }
                })
        )
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recycler_items.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { items -> retrieveList(items) }
                    }
                    Status.ERROR -> {
                        recycler_items.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recycler_items.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(items: List<SampleItem>) {
        adapter.apply {
            addUsers(items)
            notifyDataSetChanged()
        }
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "DataItems.db"
            ).build()

            //RefreshDB
            db.CharacterDao().deleteDB()

            //InsertDB
            val j = items.size
            for (i in 0 until j)
            {
                GlobalScope.launch {
                    db.CharacterDao().insertAllCharacters(Character(
                        items.get(i),
                        items.get(i).id,
                        items.get(i).remarks,
                        items.get(i).pickupTime,
                        items.get(i).goodsPicture,
                        items.get(i).deliveryFee,
                        items.get(i).surcharge,
                        items.get(i).route.start,
                        items.get(i).route.end,
                        items.get(i).sender.phone,
                        items.get(i).sender.name,
                        items.get(i).sender.email,
                        "0"))
                }
            }

    }

//    override fun onPause() {
//        super.onPause()
//
//        // save RecyclerView state
//        mBundleRecyclerViewState = Bundle()
//        val listState = recycler_items.layoutManager!!.onSaveInstanceState()
//        mBundleRecyclerViewState!!.putParcelable(KEY_RECYCLER_STATE, listState)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        // restore RecyclerView state
//        if (mBundleRecyclerViewState != null) {
//            val listState = mBundleRecyclerViewState!!.getParcelable<Parcelable>(KEY_RECYCLER_STATE)
//            recycler_items.layoutManager!!.onRestoreInstanceState(listState)
//        }
//    }

}