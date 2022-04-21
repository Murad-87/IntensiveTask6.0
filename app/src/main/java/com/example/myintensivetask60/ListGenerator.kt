package com.example.myintensivetask60

import com.example.myintensivetask60.data.Contact

object ListGenerator {
    fun generate(): List<Contact> {
        val list = (0..150).map { i ->
            Contact(
                id = i,
                name = "Joe$i",
                lastName = "Biden$i",
                phoneNumber = "89280009988_$i",
                url = "https://picsum.photos/200/300?random=$i"
            )
        }
        return list
    }

    fun gener() = arrayListOf<Contact>().apply {
        for (i in 0..150) {
            add(
                Contact(
                    id = i,
                    name = "Joe$i",
                    lastName = "Biden$i",
                    phoneNumber = "89280009988_$i",
                    url = "https://picsum.photos/200/300?random=$i"
                )
            )
        }
    }
}