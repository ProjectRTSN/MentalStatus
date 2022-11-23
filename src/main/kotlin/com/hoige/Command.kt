package com.hoige

import net.mamoe.mirai.event.events.GroupMessageEvent
import okhttp3.RequestBody.Companion.toRequestBody
import net.mamoe.mirai.event.GlobalEventChannel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okio.IOException
import okhttp3.Request

class Command {
    fun check(){
        GlobalEventChannel
            .filterIsInstance<GroupMessageEvent>()
            .subscribeAlways<GroupMessageEvent> {
                val input = it.message.contentToString()
                if (Regex("""^查询精神状态$""").matches(input))
                    it.group.sendMessage("你的精神状态是:" + request(it))
            }
    }

    private val client = OkHttpClient()

    private fun request(message: GroupMessageEvent) : String {
        val postBody = """{ "QQ":${message.sender.id} }""".trimMargin()

        val request = Request.Builder()
            .url("http://101.43.181.13:23025/select")
            .post(postBody.toRequestBody(MEDIA_TYPE))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return(response.body!!.string().trimMargin())
        }
    }

    companion object {
        val MEDIA_TYPE = "text/plain; charset=utf-8".toMediaType()
    }

}
