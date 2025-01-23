sealed class NetworkResult<T>(data: T? = null, message: String? = null) {
    data class isLoading:NetworkResult()
}