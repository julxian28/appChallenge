package pakultie.julxian.simpleproject.utils


data class ResourceClass<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ResourceClass<T> =
            ResourceClass(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResourceClass<T> =
            ResourceClass(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResourceClass<T> =
            ResourceClass(status = Status.LOADING, data = data, message = null)
    }
}