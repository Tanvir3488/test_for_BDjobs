package com.tanvir.testforforselection.Model

data class JobsInfo(
    val common: Common,
    val data: List<Data>,
    val message: String,
    val statuscode: String
)