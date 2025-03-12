package com.shahbaz.learning.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    @SerialName("about_the_brand")
    val aboutTheBrand: Any = Any(),
    @SerialName("attribute_set_id")
    val attributeSetId: String = "",
    @SerialName("brand")
    val brand: String = "",
    @SerialName("brand_banner_url")
    val brandBannerUrl: String = "",
    @SerialName("brand_name")
    val brandName: String = "",
    @SerialName("bundle_options")
    val bundleOptions: List<Any> = listOf(),
    @SerialName("celebrity_id")
    val celebrityId: Int = 0,
    @SerialName("configurable_option")
    val configurableOption: List<ConfigurableOption> = listOf(),
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("final_price")
    val finalPrice: String = "",
    @SerialName("has_options")
    val hasOptions: String = "",
    @SerialName("how_to_use")
    val howToUse: Any = Any(),
    @SerialName("id")
    val id: String = "",
    @SerialName("image")
    val image: String = "",
    @SerialName("images")
    val images: List<String> = listOf(),
    @SerialName("is_best_seller")
    val isBestSeller: Int = 0,
    @SerialName("is_following_brand")
    val isFollowingBrand: Int = 0,
    @SerialName("is_new")
    val isNew: Int = 0,
    @SerialName("is_return")
    val isReturn: Int = 0,
    @SerialName("is_salable")
    val isSalable: Boolean = false,
    @SerialName("is_sale")
    val isSale: Int = 0,
    @SerialName("is_trending")
    val isTrending: Int = 0,
    @SerialName("key_ingredients")
    val keyIngredients: Any = Any(),
    @SerialName("manufacturer")
    val manufacturer: Any = Any(),
    @SerialName("meta_description")
    val metaDescription: String = "",
    @SerialName("meta_keyword")
    val metaKeyword: Any = Any(),
    @SerialName("meta_title")
    val metaTitle: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("options")
    val options: List<Any> = listOf(),
    @SerialName("price")
    val price: String = "",
    @SerialName("related")
    val related: List<Any> = listOf(),
    @SerialName("remaining_qty")
    val remainingQty: Int = 0,
    @SerialName("returns_and_exchanges")
    val returnsAndExchanges: Any = Any(),
    @SerialName("review")
    val review: Review = Review(),
    @SerialName("shipping_and_delivery")
    val shippingAndDelivery: Any = Any(),
    @SerialName("short_description")
    val shortDescription: Any = Any(),
    @SerialName("size_chart")
    val sizeChart: Any = Any(),
    @SerialName("sku")
    val sku: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    @SerialName("upsell")
    val upsell: List<Any> = listOf(),
    @SerialName("web_url")
    val webUrl: String = "",
    @SerialName("weight")
    val weight: Any = Any(),
    @SerialName("wishlist_item_id")
    val wishlistItemId: Int = 0
)