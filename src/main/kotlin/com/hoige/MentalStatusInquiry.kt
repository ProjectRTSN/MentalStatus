package com.hoige

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object MentalStatusInquiry : KotlinPlugin(
    JvmPluginDescription(
        id = "com.hoige.mental",
        name = "Mental Status Inquiry",
        version = "0.1.9",
    ) {
        author("HoiGe")
    }
) {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onEnable() {

        GlobalScope.launch {
            Command().check()
        }
    }
}
