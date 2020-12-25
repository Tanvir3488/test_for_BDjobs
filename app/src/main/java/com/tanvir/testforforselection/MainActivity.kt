package com.tanvir.testforforselection

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanvir.testforforselection.Adapter.JobAdapter
import com.tanvir.testforforselection.Model.JobDetails
import com.tanvir.testforforselection.Repository.MainActivityRepository
import com.tanvir.testforforselection.Repository.MainActivityViewModelFactory
import com.tanvir.testforforselection.databinding.ActivityMainBinding
import org.json.JSONException

class MainActivity : AppCompatActivity() {
   private val list: ArrayList<JobDetails> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainviewmodel: MainActivityViewModel
    private lateinit var mainrepository: MainActivityRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            com.tanvir.testforforselection.R.layout.activity_main
        )
        mainrepository = MainActivityRepository()
        val factore = MainActivityViewModelFactory(mainrepository)
        mainviewmodel = ViewModelProvider(this, factore).get(MainActivityViewModel::class.java)
        binding.mainViewModel = mainviewmodel

        initJoblist()
        binding.joblist.adapter = JobAdapter(list){ jobInfo: JobDetails ->
            listItemClick(
                jobInfo
            )
        }

        mainviewmodel.jobsInfo.observe(this, Observer {
            /*val list: ArrayList<JobDetails> = ArrayList();*/
            list.clear()
            Log.e("data", it.body()?.getAsJsonArray("data")?.size().toString())

            try {
                for (item in it.body()?.getAsJsonArray("data")!!) {
                    Log.e("Title",item.asJsonObject["jobDetails"].asJsonObject["Title"].asString)




                    val jobDetails = JobDetails(
                        item.asJsonObject["jobDetails"].asJsonObject["ApplyInstruction"].asString,
                        item.asJsonObject["jobDetails"].asJsonObject["CompanyName"].asString,
                        item.asJsonObject["jobDetails"].asJsonObject["LastDate"].asString,
                        item.asJsonObject["jobDetails"].asJsonObject["Title"].asString,
                        item.asJsonObject["IsFeatured"].asBoolean,
                        if(item.asJsonObject["maxExperience"].isJsonNull) 0 else item.asJsonObject["maxExperience"].asInt ,
                        item.asJsonObject["maxSalary"].asString,
                        if(item.asJsonObject["minExperience"].isJsonNull) 0 else item.asJsonObject["minExperience"].asInt,
                        item.asJsonObject["minSalary"].asString,
                        item.asJsonObject["logo"].asString,
                    )

                    list.add(jobDetails)

                }
            }catch (e:JSONException){

                Log.e("jsox",e.localizedMessage)
            }


            binding.joblist.adapter!!.notifyDataSetChanged();




        })





    }

    private fun listItemClick(jobInfo: JobDetails) {

        Log.e("click","click");
        val builder = AlertDialog.Builder(this)
        builder.setMessage(Html.fromHtml(jobInfo.ApplyInstruction))
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })


        builder.create()
        builder.show()
    }


    private fun initJoblist() {
        binding.joblist.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )


    }
}