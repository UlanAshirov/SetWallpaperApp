package com.example.setwallpaperapp.presentation.ui.activity

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.setwallpaperapp.R
import com.example.setwallpaperapp.databinding.ActivityMainBinding
import com.example.setwallpaperapp.presentation.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val adapter: MainAdapter by lazy { MainAdapter(this::onItemClick) }
    private var contentHasLoaded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        binding.rvImage.adapter = adapter
    }

      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
          menuInflater.inflate(R.menu.category_menu, menu)
          val item = menu?.findItem(R.id.spinner)
          val spinner = item?.actionView as AppCompatSpinner

          val categories = resources.getStringArray(R.array.categories)
          val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.dropdown_item, categories)
          arrayAdapter.setDropDownViewResource(R.layout.dropdown_item)
          spinner.adapter = arrayAdapter

          spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
              override fun onItemSelected(
                  parent: AdapterView<*>?,
                  view: View?,
                  position: Int,
                  id: Long
              ) {
                  lifecycleScope.launchWhenCreated {
                      mainViewModel.isConnected.collectLatest { isConnectivity ->
                          if (isConnectivity) {
                              mainViewModel.getImage(categories[position])
                              binding.checkInternet.root.gone()
                              binding.rvImage.visible()
                          } else {
                              binding.checkInternet.root.visible()
                              binding.rvImage.gone()
                          }
                      }
                  }
              }

              override fun onNothingSelected(parent: AdapterView<*>?) {}
          }
          return true
      }

    private fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collect { hitUI ->
                    when (hitUI) {
                        is PixabayState.Success -> {
                            adapter.submitList(hitUI.data)
                            contentHasLoaded = true
                            binding.progress.gone()
                            binding.rvImage.visible()
                        }
                        is PixabayState.Loading -> {
                            binding.progress.visible()
                            binding.rvImage.gone()
                        }
                        is PixabayState.Error -> {
                            Log.e("ololo", hitUI.message)
                            showToast("Упс")
                            binding.rvImage.gone()
                            binding.progress.gone()
                        }
                    }
                }
            }
        }
    }

    private fun onItemClick(url: String) {
        AlertDialog.Builder(this)
            .setTitle("Установить в качестве обоев?")
            .setNegativeButton("Нет", null)
            .setPositiveButton("Да") { _: DialogInterface?, _: Int ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val inputStream = URL(url).openStream()
                    WallpaperManager.getInstance(this@MainActivity).setStream(inputStream)
                }
            }
            .show()
    }
}