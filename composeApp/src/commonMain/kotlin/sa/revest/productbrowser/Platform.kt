package sa.revest.productbrowser

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform