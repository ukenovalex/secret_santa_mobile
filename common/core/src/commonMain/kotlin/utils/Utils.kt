package utils

object Utils {
    fun validateEmail(email: String): Boolean {
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(flagstudio)(\\.)(ru)"
        return EMAIL_REGEX.toRegex().matches(email);
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 5
    }
}