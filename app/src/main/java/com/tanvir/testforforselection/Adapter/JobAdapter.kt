package com.tanvir.testforforselection.Adapter

import android.app.AlertDialog
import android.app.job.JobInfo
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tanvir.testforforselection.Model.JobDetails

import com.tanvir.testforforselection.R
import com.tanvir.testforforselection.databinding.JobDetailsBinding


class JobAdapter(
     private val jobInfoList: ArrayList<JobDetails>,
     private val clickListener: (JobDetails) -> Unit
):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val binding:JobDetailsBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.job_details,
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(jobInfoList[position],clickListener)


    }

    override fun getItemCount(): Int {
        return jobInfoList.size
    }


}


class MyViewHolder(val binding: JobDetailsBinding):RecyclerView.ViewHolder(binding.root){




    fun bind(jobInfo: JobDetails,clickListener: (JobDetails) -> Unit){
        Picasso.get().load(jobInfo.logo)
        .into(binding.logo)

        binding.companyName.text=jobInfo.CompanyName
        binding.jobtitle.text=jobInfo.Title
        binding.deadline.text=jobInfo.LastDate

        if (jobInfo.maxSalary=="" && jobInfo.minSalary=="")
            binding.salary.text="Negotiable"
        else if (jobInfo.maxSalary!="" && jobInfo.minSalary=="")
            binding.salary.text="Maximum "+jobInfo.maxSalary
        else if (jobInfo.maxSalary=="" && jobInfo.minSalary!="")
            binding.salary.text="Minimum "+jobInfo.minSalary
        else if (jobInfo.maxSalary!="" && jobInfo.minSalary!="")
            binding.salary.text="Between "+jobInfo.minSalary+" To "+jobInfo.maxSalary

        if (jobInfo.maxExperience==0 && jobInfo.minExperience==0)
            binding.exparince.text="Not Specified"
        if (jobInfo.maxExperience>0 && jobInfo.minExperience==0)
            binding.exparince.text="Maximum "+jobInfo.maxExperience+" years"
        if (jobInfo.maxExperience==0 && jobInfo.minExperience>0)
            binding.exparince.text="Minimum "+jobInfo.minExperience+" years"
        if (jobInfo.maxExperience!=0 && jobInfo.minExperience!=0)
            binding.exparince.text=jobInfo.minExperience.toString()+" To "+jobInfo.maxExperience.toString()+" years"



        binding.card.setOnClickListener{
            clickListener(jobInfo)
        }
        if (jobInfo.IsFeatured)
            binding.card.setCardBackgroundColor(Color.parseColor("#F6F5EB"))




    }

}