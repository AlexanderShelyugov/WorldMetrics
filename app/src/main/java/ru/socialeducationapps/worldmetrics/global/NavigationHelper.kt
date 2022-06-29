package ru.socialeducationapps.worldmetrics.global

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

class NavigationHelper private constructor() {
    companion object {
        fun bindNavigation(view: View, action: NavDirections) {
            view.setOnClickListener {
                navigateTo(it.findNavController(), action)
            }
        }

        fun navigateTo(controller: NavController, action: NavDirections) {
            controller.navigate(action)
        }
    }
}