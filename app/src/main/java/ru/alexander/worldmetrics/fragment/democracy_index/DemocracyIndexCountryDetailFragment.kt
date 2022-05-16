package ru.alexander.worldmetrics.fragment.democracy_index

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.viewmodel.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    InjectableFragment(R.layout.democracy_index_country_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCountryName = view.findViewById<TextView>(R.id.tv_di_cn)
        val tvIndexValue = view.findViewById<TextView>(R.id.tv_di_di)
        val tvElectoralProcessAndPluralism =
            view.findViewById<TextView>(R.id.tv_di_epap)
        val tvFunctioningOfGovernment =
            view.findViewById<TextView>(R.id.tv_di_fog)
        val tvPoliticalParticipation =
            view.findViewById<TextView>(R.id.tv_di_pp)
        val tvPoliticalCulture =
            view.findViewById<TextView>(R.id.tv_di_pc)
        val tvCivilLiberties =
            view.findViewById<TextView>(R.id.tv_di_cl)

        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        val country = args.countryName
        val model: DemocracyIndexCountryDetailViewModel by activityViewModels()
        model.lastYearData.observe(viewLifecycleOwner) {
            tvCountryName.text = it.country
            tvIndexValue.text = it.democracyIndex.toString()
            tvElectoralProcessAndPluralism.text =
                it.electoralProcessAndPluralism.toString()
            tvFunctioningOfGovernment.text = it.functioningOfGovernment.toString()
            tvPoliticalParticipation.text = it.politicalParticipation.toString()
            tvPoliticalCulture.text = it.politicalCulture.toString()
            tvCivilLiberties.text = it.civilLiberties.toString()
        }
        model.setCountry(country)
    }
}