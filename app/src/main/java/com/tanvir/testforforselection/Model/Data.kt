package com.tanvir.testforforselection.Model

data class Data(
    val IsFeatured: Boolean,
    val deadline: String,
    val jobDetails: JobDetails,
    val jobTitle: String,
    val logo: String,
    val maxExperience: Int,
    val maxSalary: String,
    val minExperience: Int,
    val minSalary: String,
    val recruitingCompanysProfile: String
)