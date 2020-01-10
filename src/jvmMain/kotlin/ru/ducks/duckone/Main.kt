package ru.ducks.duckone

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.TelegramBotsApi
import ru.ducks.duckone.bot.DuckBot

fun main() {
    ApiContextInitializer.init()
    val botOptions = DefaultBotOptions()
    botOptions.enableTor()
    val bot = DuckBot(botOptions)
    startBot(bot)
}

private fun startBot(bot: DuckBot) {
    val api = TelegramBotsApi()
    api.registerBot(bot)
}

private fun DefaultBotOptions.enableTor() {
    startTor()
    useTorProxy()
}

private fun startTor() {
    Runtime.getRuntime().exec("elevate net start tor")
}

private fun DefaultBotOptions.useTorProxy() {
    proxyType = DefaultBotOptions.ProxyType.SOCKS5
    proxyHost = "127.0.0.1"
    proxyPort = 8888
}