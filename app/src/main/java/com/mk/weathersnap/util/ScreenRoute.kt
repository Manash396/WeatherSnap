package com.mk.weathersnap.util

sealed class ScreenRoute(val route: String) {
    object Search : ScreenRoute("search")
    object Camera : ScreenRoute("camera")
    object Reports : ScreenRoute("reports")
    object CreateReport : ScreenRoute("createReport")
}