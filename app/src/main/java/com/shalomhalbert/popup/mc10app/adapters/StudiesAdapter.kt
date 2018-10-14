package com.shalomhalbert.popup.mc10app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shalomhalbert.popup.mc10app.R
import com.shalomhalbert.popup.mc10app.models.Study
import kotlinx.android.synthetic.main.list_item_studies.view.*
import org.w3c.dom.Text
import java.util.*

/**
 * [RecyclerView.Adapter] for [StudiesFragment]
 * @param timeZone provides [User]'s timezone
 */
class StudiesAdapter(val timeZone: TimeZone): RecyclerView.Adapter<StudiesAdapter.StudiesViewHolder>() {
    //Values adapted to RecyclerView
    private var studies = listOf<Study>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudiesViewHolder  =
            StudiesViewHolder(LayoutInflater.from(p0.context)
                    .inflate(R.layout.list_item_studies, p0, false))

    override fun getItemCount(): Int = studies.size

    override fun onBindViewHolder(p0: StudiesViewHolder, p1: Int) {
        p0.displayNameTextView.text = studies[p1].displayName
        p0.dateTextView.text = studies[p1].formattedDate(timeZone)
        p0.titleTextView.text = studies[p1].title
    }

    fun addStudies(studies: Array<Study>?) {
        this.studies = studies!!.toList()
        notifyDataSetChanged()
    }

    /** [RecyclerView.ViewHolder] for [StudiesAdapter]*/
    class StudiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val displayNameTextView: TextView = itemView.displayName
        val dateTextView: TextView = itemView.date
        val titleTextView: TextView = itemView.title
    }
}