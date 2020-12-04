package com.mytest.myapplication.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mytest.myapplication.R
import com.mytest.myapplication.network.model.RepoInfo


class Adapter(events: ClickEvents) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var reposList: List<RepoInfo> = emptyList()
    private val listener: ClickEvents = events
//
//    val cardView: CardView? = null
//    val author: TextView? = null
//    val name: TextView? = null
//    val description: TextView? = null
//    val avatar: ImageView? = null



    fun setNewList(newList: List<RepoInfo>) {
        reposList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = reposList[position]
        holder.bind(currentItem, listener)

        holder.author.text = currentItem.author
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        Glide.with(holder.itemView.context).load(currentItem.avatar)
            .apply(RequestOptions.circleCropTransform()).into(holder.avatar)

        //holder.cardView.setOnClickListener { listener.onViewClicked(currentItem , holder.cardView) }
        var url = currentItem.url
        //holder.cardView.setOnClickListener { }
    }

    override fun getItemCount() = reposList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repos: RepoInfo, listener: ClickEvents) = with(itemView) {
//            author.text = repos.author
//            name.text = repos.name
//            description.text = repos.description
            cardView.setOnClickListener { listener.onViewClicked(repos, cardView) }
        }

        val cardView: CardView = itemView.findViewById(R.id.cardview)
        val author: TextView = itemView.findViewById(R.id.tvAuthor)
        val name: TextView = itemView.findViewById(R.id.tvName)
        val description: TextView = itemView.findViewById(R.id.tvDescribtion)
        val avatar: ImageView = itemView.findViewById(R.id.ivAvatar)


    }

    interface ClickEvents {
        fun onViewClicked(repo: RepoInfo, view: View)
    }
}