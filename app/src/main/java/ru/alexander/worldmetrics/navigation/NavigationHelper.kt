package ru.alexander.worldmetrics.navigation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

class NavigationHelper private constructor() {
    companion object {
        fun bindNavigation(view: View, viewId: Int, action: NavDirections) {
            view.findViewById<View>(viewId).setOnClickListener {
                navigateTo(it.findNavController(), action)
            }
        }

        fun navigateTo(controller: NavController, action: NavDirections) {
            controller.navigate(action)
        }
    }
}