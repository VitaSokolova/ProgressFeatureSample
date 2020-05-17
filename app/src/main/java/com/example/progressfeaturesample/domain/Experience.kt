package com.example.progressfeaturesample.domain

sealed class Experience

class WorkingExperience : Experience()
class AboutMe(val text: String) : Experience()