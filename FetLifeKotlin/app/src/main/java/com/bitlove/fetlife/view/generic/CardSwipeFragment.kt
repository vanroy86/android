package com.bitlove.fetlife.view.generic

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.bitlove.fetlife.R
import com.bitlove.fetlife.logic.viewmodel.CardDetailViewModel
import com.bitlove.fetlife.logic.viewmodel.CardListViewModel

import kotlinx.android.synthetic.main.fragment_card_swipe.*

class CardSwipeFragment : BindingFragment<ViewDataBinding, CardDetailViewModel>() {
    companion object {
        const val ARG_CARD_IDS = "ARG_CARD_IDS"
        const val ARG_SELECTED_POSITION = "ARG_SELECTED_POSITION"
        private const val ARG_CARD_TYPE = "ARG_CARD_TYPE"
        fun newInstance(cardType: CardDetailViewModel.CardType,cardIds: List<String>, selectedPosition: Int) : CardSwipeFragment {
            val fragment = CardSwipeFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARG_CARD_TYPE, cardType)
            bundle.putStringArrayList(ARG_CARD_IDS,ArrayList<String>(cardIds))
            bundle.putInt(ARG_SELECTED_POSITION,selectedPosition)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var cardType: CardDetailViewModel.CardType
    private lateinit var cardIds: ArrayList<String>
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardType = arguments.getSerializable(ARG_CARD_TYPE) as CardDetailViewModel.CardType
        cardIds = arguments.getStringArrayList(ARG_CARD_IDS)
        selectedPosition = arguments.getInt(ARG_SELECTED_POSITION)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_card_swipe
    }

    override fun getViewModelClass(): Class<CardDetailViewModel>? {
        return null
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardSwipeAdapter(cardType, cardIds, fragmentManager)
        card_view_pager.adapter = adapter
        card_view_pager.currentItem = selectedPosition
        card_view_pager.slideEnabled = true
    }

}