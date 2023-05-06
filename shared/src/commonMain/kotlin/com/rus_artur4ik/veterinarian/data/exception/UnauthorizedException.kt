package com.rus_artur4ik.veterinarian.data.exception


class UnauthorizedException(override val message: String? = "Вы не авторизованы")
    : Exception(message)