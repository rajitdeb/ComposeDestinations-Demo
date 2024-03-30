package com.rajit.composedestinationsdemo.util

import com.rajit.composedestinationsdemo.model.Person

object Constants {

    val personList = arrayListOf(
        Person(
            name = "Rajit Deb",
            address = "Silchar, Assam",
            age = 25,
            bio = "I'm a passionate Android Developer. \n\nCurrently working in Sebaguide, a student organization that changed the way students approach exam\'s preparation.",
            countryOfResidence = "India",
            projects = arrayListOf(
                "https://github.com/rajitdeb/WorldHeritages"
            ),
            profileImgURL = "https://avatars.githubusercontent.com/u/33742708?v=4",
            socialLinks = arrayListOf(
                "https://linkedin.com/in/imrajit",
                "https://instagram.com/rajit.deb",
                "https://facebook.com/iRajitDeb"
            )
        )
    )

    val tagList = arrayListOf(
        "All",
        "Cultural",
        "Mixed",
        "Natural"
    )

    val countryList = arrayListOf(
        "All",
        "India"
    )

}