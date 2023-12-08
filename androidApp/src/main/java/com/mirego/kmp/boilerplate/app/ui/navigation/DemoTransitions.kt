package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object DemoTransitions {
    private const val transitionDuration = 300

    object Modal {
        val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(transitionDuration)
            )
        }
        val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(targetAlpha = 0.9f, animationSpec = tween(transitionDuration))
        }
        val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(initialAlpha = 0.9f, animationSpec = tween(transitionDuration))
        }
        val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(transitionDuration)
            )
        }
    }

    object InTab {
        val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(transitionDuration)
            )
        }
        val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            fadeOut(targetAlpha = 0.9f, animationSpec = tween(transitionDuration))
        }
        val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            fadeIn(initialAlpha = 0.9f, animationSpec = tween(transitionDuration))
        }
        val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    }
}
