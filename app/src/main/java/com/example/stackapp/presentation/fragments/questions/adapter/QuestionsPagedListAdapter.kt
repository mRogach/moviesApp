package com.example.stackapp.presentation.fragments.questions.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.stackapp.R
import com.example.stackapp.data.models.question.Question
import com.example.stackapp.presentation.basics.adapter.BaseListenerViewHolder
import com.example.stackapp.presentation.basics.adapter.BasePagedListAdapter
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class QuestionsPagedListAdapter @Inject constructor(diffUtilCallback: DiffUtil.ItemCallback<Question>) :
    BasePagedListAdapter<Question, QuestionsPagedListAdapter.QuestionViewHolder>(diffUtilCallback) {

    override fun getItemViewId() = R.layout.item_question

    override fun instantiateViewHolder(view: View?) = QuestionViewHolder(view)

    inner class QuestionViewHolder(itemView: View?) : BaseListenerViewHolder<Question>(itemView) {

        private val tvTitle by lazy { itemView?.findViewById(R.id.tvTitle) as TextView? }
//        private val ivMovieBackground by lazy { itemView?.findViewById(R.id.ivMovieBackground) as ImageView? }

        override fun onBind(item: Question, onItemClickListener: OnItemClickListener?) {
            tvTitle?.text = item.title
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