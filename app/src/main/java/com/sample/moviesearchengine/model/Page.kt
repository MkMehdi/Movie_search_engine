package com.sample.moviesearchengine.model

class Page(var page:Int,
           var total_results:Int,
           var total_pages: Int,
           var results:List<Movie>)