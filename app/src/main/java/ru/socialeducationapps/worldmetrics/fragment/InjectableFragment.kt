package ru.socialeducationapps.worldmetrics.fragment

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class InjectableFragment(layoutId: Int) : Fragment(layoutId)