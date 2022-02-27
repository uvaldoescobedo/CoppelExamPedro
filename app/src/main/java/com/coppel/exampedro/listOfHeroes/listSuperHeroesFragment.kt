package com.coppel.exampedro.listOfHeroes

import Hero
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.coppel.exampedro.R
import com.coppel.exampedro.Repository.SuperHeroesRepository
import com.coppel.exampedro.listOfHeroes.adapter.HeroAdapter
import com.coppel.exampedro.databinding.FragmentListSuperHeroesBinding
import com.coppel.exampedro.viewModel.SuperHeroesViewModel
import com.coppel.exampedro.webService.WebServiceApi

class listSuperHeroesFragment : Fragment() {

    lateinit var b: FragmentListSuperHeroesBinding
    lateinit var model: SuperHeroesViewModel
    lateinit var adapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = SuperHeroesViewModel(SuperHeroesRepository(WebServiceApi().getApi()))
        model.getCharaters()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentListSuperHeroesBinding.inflate(layoutInflater)
        adapter = HeroAdapter(arrayListOf(), ::ClickONSuperHer)

        b.recycler.adapter = adapter

        b.root.setOnClickListener {
            findNavController().navigate(R.id.action_listSuperHeroesFragment_to_heroDetailFragment)
        }

        model.responseData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updatelist(it.data.results)
                model.countlist = adapter.list.count()
            }
        })

        b.nedScrool.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null) {
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    // cuando el ultimo item se esta viendo incrementamos el tamaño de pagina y mandamos la peticion
                    model.getCharaters()

                }
            }
        })

        return b.root
    }

    private fun ClickONSuperHer(hero: Hero) {
        var action =
            listSuperHeroesFragmentDirections.actionListSuperHeroesFragmentToHeroDetailFragment(hero)
        findNavController().navigate(action)
    }

}