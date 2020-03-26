package com.albuquerque.moviecatalog.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.core.utils.ZoomOutPageTransformer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_image_slide_item.view.*
import kotlinx.android.synthetic.main.layout_image_slider.view.*
import kotlinx.android.synthetic.main.layout_image_slider_dot.view.*


class ImageSlider @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var images: List<String> = arrayListOf()

    private var currentSlide: Int = 0

    private lateinit var dotsAdapter: HorizontalDotsAdapter

    private val pagerCallback = object: ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentSlide = position
            dotsAdapter.notifyDataSetChanged()

        }
    }

    fun setup(images: List<String>) {
        View.inflate(context, R.layout.layout_image_slider, this)

        this.images = images

        dotsAdapter = HorizontalDotsAdapter(images.size)

        with(pager) {
            registerOnPageChangeCallback(pagerCallback)
            adapter = ScreenSlidePagerAdapter()
            setPageTransformer(ZoomOutPageTransformer(0.95f, 0.5f))
        }

        recyclerViewDots.adapter = dotsAdapter

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wrapHeight = width
        val wrapWidth = height

        setMeasuredDimension(wrapHeight, wrapWidth)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * Adapter for setting up [ViewPager2]
     * */
    private inner class ScreenSlidePagerAdapter: RecyclerView.Adapter<ScreenSlidePagerAdapter.SliderViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
            return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slide_item, parent, false))
        }

        override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
            holder.bind(images[position])
        }

        override fun getItemCount(): Int = images.size

        inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bind(url: String) {

                with(itemView) {
                    Glide.with(context).load(url).fitCenter().into(slideItem)
                }
            }
        }

    }

    /**
     * Adapter to show to the user the current position in the [ViewPager2]
     * */
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

        private inner class HorizontalDotsViewHolder(view: View): RecyclerView.ViewHolder(view) {

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

}