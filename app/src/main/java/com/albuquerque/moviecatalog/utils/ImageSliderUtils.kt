package com.albuquerque.moviecatalog.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.albuquerque.moviecatalog.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_image_slide_item.view.*
import kotlinx.android.synthetic.main.layout_image_slider_dot.view.*

class ImageSliderUtils(
        private val pager: ViewPager2,
        private val rv: RecyclerView,
        private var images: List<String>
) {
    private lateinit var dotsAdapter: HorizontalDotsAdapter
    private var currentSlide = 0

    fun setupViewPager() {
        dotsAdapter = HorizontalDotsAdapter(images.size)

        with(pager) {
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentSlide = position
                    dotsAdapter.notifyDataSetChanged()
                }
            })
            adapter = ScreenSlidePagerAdapter(images)
            setPageTransformer(ZoomOutPageTransformer(0.95f, 0.5f))
        }

        rv.adapter = dotsAdapter
    }

    private inner class HorizontalDotsAdapter(
            private val size: Int
    ) : RecyclerView.Adapter<HorizontalDotsAdapter.HorizontalDotsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalDotsViewHolder {
            return HorizontalDotsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slider_dot, parent, false))
        }

        override fun onBindViewHolder(holder: HorizontalDotsViewHolder, position: Int) {
            holder.bind()
        }

        override fun getItemCount(): Int = size

        inner class HorizontalDotsViewHolder(view: View): RecyclerView.ViewHolder(view) {

            fun bind() {
                with(itemView) {
                    if(currentSlide == adapterPosition) {
                        dotChecked.visibility = View.VISIBLE
                        dotNotChecked.visibility = View.GONE
                    } else {
                        dotChecked.visibility = View.GONE
                        dotNotChecked.visibility = View.VISIBLE
                    }
                }

            }

        }

    }

    private inner class ScreenSlidePagerAdapter(
            var images: List<String>
    ): RecyclerView.Adapter<ScreenSlidePagerAdapter.SliderViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
            return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slide_item, parent, false))
        }

        override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
            holder.bind(images[position])
        }

        override fun getItemCount(): Int = images.size

        inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bind(url: String) {
                Glide.with(itemView.context).load(url).fitCenter().into(itemView.slideItem)
            }
        }

    }

}