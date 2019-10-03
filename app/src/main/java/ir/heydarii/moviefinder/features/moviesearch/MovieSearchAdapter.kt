package ir.heydarii.moviefinder.features.moviesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.heydarii.moviefinder.R
import ir.heydarii.moviefinder.pojo.Result
import ir.heydarii.moviefinder.utils.Consts
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieSearchAdapter(private val list: List<Result>) : RecyclerView.Adapter<MovieSearchAdapter.MovieSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieSearchViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class MovieSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Result) {
            itemView.txtTitle.text = movie.title
            itemView.txtReleaseDate.text = movie.release_date
            itemView.rateMovie.rating = movie.vote_average.toFloat() / 2
            itemView.txtDetails.text = movie.overview
            Picasso.get().load(Consts.SMALL_PIC_URL + movie.backdrop_path).into(itemView.imgPoster)

        }

    }
}