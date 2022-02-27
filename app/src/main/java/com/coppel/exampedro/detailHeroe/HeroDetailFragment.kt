package com.coppel.exampedro.detailHeroe

import Hero
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.coppel.exampedro.R
import com.coppel.exampedro.Repository.SuperHeroesRepository
import com.coppel.exampedro.listOfHeroes.adapter.imagenDescriptionAdapter
import com.coppel.exampedro.databinding.FragmentHeroDetailBinding
import com.coppel.exampedro.viewModel.SuperHeroesViewModel
import com.coppel.exampedro.webService.WebServiceApi
import imagenDescription

class HeroDetailFragment : Fragment() {
    lateinit var b: FragmentHeroDetailBinding
    private val args: HeroDetailFragmentArgs by navArgs()
    lateinit var model: SuperHeroesViewModel
    lateinit var ComicsAdapter: imagenDescriptionAdapter
    lateinit var EventsAdapter: imagenDescriptionAdapter
    lateinit var SeriesAdapter: imagenDescriptionAdapter
    lateinit var StoriesAdapter: imagenDescriptionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentHeroDetailBinding.inflate(layoutInflater)
        model = SuperHeroesViewModel(SuperHeroesRepository(WebServiceApi().getApi()))
        ComicsAdapter = imagenDescriptionAdapter(arrayListOf())
        EventsAdapter = imagenDescriptionAdapter(arrayListOf())
        SeriesAdapter = imagenDescriptionAdapter(arrayListOf())
        StoriesAdapter = imagenDescriptionAdapter(arrayListOf())

        // Inflate the layout for this fragment
        if (args.hero != null) {
            cargarInterfaz(args.hero)

        } else {
            activity?.onBackPressed()
        }
        return b.root
    }

    private fun cargarInterfaz(hero: Hero) {
        b.apply {
            comics.recycler.adapter = ComicsAdapter
            events.recycler.adapter = EventsAdapter
            stories.recycler.adapter = StoriesAdapter
            series.recycler.adapter = SeriesAdapter
            btnBack.setOnClickListener{activity?.onBackPressed()}

            firstName.text = hero.name
            Glide.with(b.root.context).load("${hero.thumbnail.path}.${hero.thumbnail.extension}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photo)
            description.text = hero.description

        }
        model.getComis(hero.id.toString())
        model.getEvents(hero.id.toString())
        model.getSeries(hero.id.toString())
        model.getStories(hero.id.toString())
        model.getProgress().observe(viewLifecycleOwner, Observer {
            b.progressBar.isVisible = it
        })

        model.responseComics.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                var list: ArrayList<imagenDescription> = arrayListOf()

                it.data.results.forEach {
                    list.add(
                        imagenDescription(
                            it.title,
                            "${it.thumbnail?.path}.${it.thumbnail?.extension}"

                        )
                    )
                }
                if (list.isNotEmpty()) {
                    b.comics.root.isVisible = true
                    b.comics.tittleTxt.text = getString(R.string.comics_tittle)
                }
                ComicsAdapter.updatelist(list)
            }

        })
        model.responseEvents.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                var list: ArrayList<imagenDescription> = arrayListOf()

                it.data.results.forEach {
                    list.add(
                        imagenDescription(
                            it.title,
                            "${it.thumbnail?.path}.${it.thumbnail?.extension}"

                        )
                    )
                }
                if (list.isNotEmpty()) {
                    b.events.root.isVisible = true
                    b.events.tittleTxt.text = getString(R.string.events_tittle)
                }
                EventsAdapter.updatelist(list)
            }
        })
        model.responseSeries.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                var list: ArrayList<imagenDescription> = arrayListOf()

                it.data.results.forEach {
                    list.add(
                        imagenDescription(
                            it.title,
                            "${it.thumbnail?.path}.${it.thumbnail?.extension}"

                        )
                    )
                }
                if (list.isNotEmpty()) {
                    b.series.root.isVisible = true
                    b.series.tittleTxt.text = getString(R.string.series_tittle)
                }
                SeriesAdapter.updatelist(list)
            }

        })
        model.responseStories.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                var list: ArrayList<imagenDescription> = arrayListOf()

                it.data.results.forEach {
                    list.add(
                        imagenDescription(
                            it.title,
                            "${it.thumbnail?.path}.${it.thumbnail?.extension}"

                        )
                    )
                }
                if (list.isNotEmpty()) {
                    b.stories.root.isVisible = true
                    b.stories.tittleTxt.text = getString(R.string.stories_tittle)
                }
                StoriesAdapter.updatelist(list)
            }

        })
    }


}