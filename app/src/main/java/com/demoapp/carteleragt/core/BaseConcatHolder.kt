package com.demoapp.carteleragt.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatHolder<T>(itemview: View) : RecyclerView.ViewHolder(itemview) {
    abstract fun bind(adapter: T)
}