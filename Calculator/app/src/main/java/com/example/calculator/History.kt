package com.example.calculator

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class History(): RealmObject() {
    @PrimaryKey
    var history: String = ""

}