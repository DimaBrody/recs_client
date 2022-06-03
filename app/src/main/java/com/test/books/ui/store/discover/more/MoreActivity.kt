package com.test.books.ui.store.discover.more

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.squareup.picasso.Picasso
import com.test.books.R
import com.test.books.databinding.ActivityMoreBinding
import com.test.books.ui.base.BaseActivity
import com.test.books.ui.store.discover.adapters.DiscoverBooksSmallAdapter
import com.test.books.utils.ui.setStatusBarGradient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoreActivity : BaseActivity() {


    private val binding: ActivityMoreBinding by binding(R.layout.activity_more)
    private val viewModel: MoreViewModel by viewModels()

    @Inject lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setStatusBarGradient(this)

        binding.adapter = DiscoverBooksSmallAdapter(listOf(), picasso)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val type = intent.getStringExtra("type")
        val title = intent.getStringExtra("title")

        if(title != null){
            binding.storeToolbarTitle.text = title
        }

        binding.storeToolbarSearchButton.setOnClickListener {
            finish()
        }

        if (type != null) {
            viewModel.loadData(type, 100)
        }
    }


}