package com.albuquerque.moviecatalog.app.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.view.activity.MainActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE = "MOVIE"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appBar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()
    }

}
