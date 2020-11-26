package com.example.utilities.passwordmanager.core.generator

interface PasswordGenerator {
    fun generatePassword(length: Int): String
}