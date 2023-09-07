package com.example.contactapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ContactListItemHelper(val con: Context, private val adapter: ContactAdapter): ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when(viewHolder) {
            is ContactAdapter.MultiViewHolder1 -> {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition())
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + viewHolder.phoneNumber))
                viewHolder.binding.root.context.startActivity(intent)
            }
            is ContactAdapter.MultiViewHolder2 -> {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition())
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + viewHolder.phoneNumber))
                viewHolder.binding.root.context.startActivity(intent)
            }
            else -> {}
        }
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            when(viewHolder) {
                is ContactAdapter.MultiViewHolder1 -> {
                    val view = viewHolder.swipeTarget
                    getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
                }
                is ContactAdapter.MultiViewHolder2 -> {
                    val view = viewHolder.swipeTarget
                    getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
                }
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        when(viewHolder) {
            is ContactAdapter.MultiViewHolder1 -> {
                getDefaultUIUtil().clearView(viewHolder.swipeTarget)
            }
            is ContactAdapter.MultiViewHolder2 -> {
                getDefaultUIUtil().clearView(viewHolder.swipeTarget)
            }
        }
    }

}