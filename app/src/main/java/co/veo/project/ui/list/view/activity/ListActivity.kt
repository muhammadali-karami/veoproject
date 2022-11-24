package co.veo.project.ui.list.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.veo.project.databinding.ActivityListBinding
import co.veo.project.ui.list.view_model.ListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

    }
}