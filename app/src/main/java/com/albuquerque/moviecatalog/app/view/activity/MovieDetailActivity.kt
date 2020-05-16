package com.albuquerque.moviecatalog.app.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        this.setSupportActionBar(toolbar)

        val movie = intent?.getSerializableExtra(MOVIE) as MovieUI
        Toast.makeText(this, movie.title, Toast.LENGTH_LONG).show()

        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) { //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                //Check if the view is collapsed
                /*if (scrollRange + verticalOffset == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(context!!, R.color.YOUR_COLLAPSED_COLOR))
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(context!!, R.color.OTHER_COLOR))
                }*/

                Log.d("teste", "$verticalOffset, ${appBarLayout.totalScrollRange}")

            }
        })

        /*ValueAnimator.ofFloat(1f, 0f).apply {
            addUpdateListener { animation ->
                appBar.alpha = (animation.animatedValue as Float)
            }
        }.start()*/
    }
}
