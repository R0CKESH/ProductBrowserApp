package sa.revest.productbrowser.data.api


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import sa.revest.productbrowser.data.model.CategoryModel
import sa.revest.productbrowser.data.model.ProductListResponse

class ProductApiService {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private val BASE_URL = "dummyjson.com"

    suspend fun getProducts(): ProductListResponse {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("products")
            }
        }.body()
    }

    suspend fun searchProducts(query: String): ProductListResponse {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("products/search")
                parameters.append("q", query)
            }
        }.body()
    }

    suspend fun getCategories(): List<CategoryModel> {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("products/categories")
            }
        }.body()
    }

    suspend fun getProductsByCategory(category: String): ProductListResponse {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("products/category/$category")
            }
        }.body()
    }
}