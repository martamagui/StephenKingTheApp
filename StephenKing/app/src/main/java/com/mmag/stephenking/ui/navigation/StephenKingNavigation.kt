package com.mmag.stephenking.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mmag.stephenking.ui.screens.bookDetailScreen.BookDetailScreen
import com.mmag.stephenking.ui.screens.bookListScreen.BookListScreen


@Composable
fun StephenKingNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = BookListDestination
    ) {

        composable<BookListDestination> {
            BookListScreen(
                onBookClick = { bookId ->
                    navController.navigate(BookDetailDestination(bookId))
                }
            )
        }

        composable<BookDetailDestination> { backStackEntry ->
            val detail = backStackEntry.toRoute<BookDetailDestination>()
            BookDetailScreen(detail.bookId)
        }

    }
}