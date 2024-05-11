package com.example.bugit.features.home_screen.data.model

import com.google.gson.annotations.SerializedName

data class ImageUrlDto(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("status")
	val status: Int
)

data class Image(

	@field:SerializedName("extension")
	val extension: String,

	@field:SerializedName("filename")
	val filename: String,

	@field:SerializedName("mime")
	val mime: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

data class Thumb(

	@field:SerializedName("extension")
	val extension: String,

	@field:SerializedName("filename")
	val filename: String,

	@field:SerializedName("mime")
	val mime: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

data class Medium(

	@field:SerializedName("extension")
	val extension: String,

	@field:SerializedName("filename")
	val filename: String,

	@field:SerializedName("mime")
	val mime: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

data class Data(

	@field:SerializedName("display_url")
	val displayUrl: String,

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("thumb")
	val thumb: Thumb,

	@field:SerializedName("delete_url")
	val deleteUrl: String,

	@field:SerializedName("medium")
	val medium: Medium,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url_viewer")
	val urlViewer: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("size")
	val size: String,

	@field:SerializedName("width")
	val width: String,

	@field:SerializedName("expiration")
	val expiration: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("height")
	val height: String
)
