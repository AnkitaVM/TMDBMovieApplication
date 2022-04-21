package com.example.tmdbmovieapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmdbmovieapplication.databinding.MoviesActivityBinding
import com.example.tmdbmovieapplication.ui.popularmovies.PopularMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MoviesActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var binding: MoviesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, PopularMoviesFragment.newInstance())
                .addToBackStack(PopularMoviesFragment::class.simpleName)
                .commit()
        }


        binding = DataBindingUtil.setContentView(this, R.layout.movies_activity)

    }

    override fun onBackPressed() {
        val n = supportFragmentManager.backStackEntryCount
        if (n == 1) {
            showAppCloseAlert()
        } else {
            supportFragmentManager.popBackStack()
        }

    }

    private fun showAppCloseAlert() {
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(R.string.app_close_confirm)
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        this@MoviesActivity.finish()
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            }
            builder.create()
        }
        alertDialog.show()
    }
}