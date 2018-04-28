/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.codelab.step5

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

import com.example.android.codelabs.persistence.R


class CustomResultActivity : AppCompatActivity() {

    private var mShowUserViewModel: CustomResultViewModel? = null

    private var mBooksTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.db_activity)
        mBooksTextView = findViewById(R.id.books_tv)

        mShowUserViewModel = ViewModelProviders.of(this).get(CustomResultViewModel::class.java)

        populateDb()

        subscribeUiLoans()
    }

    private fun populateDb() {
        mShowUserViewModel!!.createDb()
    }

    private fun subscribeUiLoans() {
        mShowUserViewModel!!.loansResult?.observe(this, Observer { result -> mBooksTextView!!.text = result })
    }

    fun onRefreshBtClicked(view: View) {
        populateDb()
        subscribeUiLoans()
    }
}
