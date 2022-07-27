package com.example.groceryapp

data class user(var Name:String?,var Email:String?,var Id:String?) {
    fun toMap(): Map<String, Any?> {

        return mapOf<String, Any?>(
            "Name" to Name,
            "Email" to Email,
        "Id" to Id
        )
    }
}
