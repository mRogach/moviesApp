package com.example.stackapp.presentation.fragments.tags.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.stackapp.R
import com.example.stackapp.data.models.Tag
import com.example.stackapp.presentation.basics.adapter.BaseListenerViewHolder
import com.example.stackapp.presentation.basics.adapter.BasePagedListAdapter
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class TagsPagedListAdapter @Inject constructor(diffUtilCallback: DiffUtil.ItemCallback<Tag>) :
    BasePagedListAdapter<Tag, TagsPagedListAdapter.TagViewHolder>(diffUtilCallback) {

    override fun getItemViewId() = R.layout.item_tag

    override fun instantiateViewHolder(view: View?) = TagViewHolder(view)

    inner class TagViewHolder(itemView: View?) : BaseListenerViewHolder<Tag>(itemView) {

        private val tvTitle by lazy { itemView?.findViewById(R.id.tvTitle) as TextView? }
        private val tvRating by lazy { itemView?.findViewById(R.id.tvRating) as TextView? }
//        private val ivMovieBackground by lazy { itemView?.findViewById(R.id.ivMovieBackground) as ImageView? }

        override fun onBind(item: Tag, onItemClickListener: OnItemClickListener?) {
            tvTitle?.text = item.name
//            tvRating?.text = item.voteAverage.toString()
//            ivMovieBackground?.let {
//
//                Glide.with(it.context)
//                    .load("https://image.tmdb.org/t/p/w500/" + item.backdropPath)
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(it)
//            }
        }

    }
}