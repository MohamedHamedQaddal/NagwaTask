package com.app.nagwa.nagwatask.modules.media.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.app.nagwa.nagwatask.R

fun Fragment.openMediaFragment() {
    val navController = Navigation.findNavController(requireActivity(), R.id.container)
    navController.navigate(R.id.mediaFragment)
}