package gyg.amine.gygberlin.reviews.extensions

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView

/**
 * Using extension functions to improve adapter capabilities
 * check : https://antonioleiva.com/recyclerview-diffutil-kotlin/
 */
fun <T> RecyclerView.Adapter<*>.notifyNewData(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size
    })

    diff.dispatchUpdatesTo(this)

}

