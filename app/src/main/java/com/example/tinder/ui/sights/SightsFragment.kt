package com.example.tinder.ui.sights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tinder.Adapter
import com.example.tinder.R
import link.fls.swipestack.SwipeStack
import kotlin.math.abs

class SightsFragment : Fragment(), SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {

    private var list = listOf<String>(
        "dfl;jg;d",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf",
        "dsF'dsF",
        "dsfdsfdsf"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_sights, container, false)
        val swipeStack: SwipeStack = root.findViewById(R.id.swipe)
        swipeStack.setListener(this)
        swipeStack.setSwipeProgressListener(this)
        swipeStack.adapter = Adapter(list)
        return root
    }

    override fun onViewSwipedToLeft(position: Int) {

        Toast.makeText(context, "LEFT", Toast.LENGTH_SHORT).show()
    }

    override fun onViewSwipedToRight(position: Int) {
        Toast.makeText(context, "RIGHT", Toast.LENGTH_SHORT).show()
    }

    override fun onStackEmpty() {
        Toast.makeText(context, "EMPTY", Toast.LENGTH_SHORT).show()
    }

    override fun onSwipeEnd(position: Int) {
        val dislikeView: View = view!!.findViewById(R.id.dislikeView)
        dislikeView.alpha = 0.0F
        val likeView: View = view!!.findViewById(R.id.likeView)
        likeView.alpha = 0.0F
    }

    override fun onSwipeStart(position: Int) {

    }

    override fun onSwipeProgress(position: Int, progress: Float) {
        if (progress < 0 && progress > -0.6) {
            val dislikeView: View = view!!.findViewById(R.id.dislikeView)
            dislikeView.alpha = abs(progress * (1 / 0.6F))

        } else if (progress > 0 && progress < 0.6) {
            val likeView: View = view!!.findViewById(R.id.likeView)
            likeView.alpha = abs(progress * 1 / 0.6F)
        }
    }
}