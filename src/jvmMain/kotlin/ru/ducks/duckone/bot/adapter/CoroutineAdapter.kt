package ru.ducks.duckone.bot.adapter

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.meta.updateshandlers.SentCallback
import java.io.Serializable
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T : Serializable?> AbsSender.exec(method: BotApiMethod<T>): T = suspendCoroutine { cont ->
    executeAsync(method, object : SentCallback<T> {
        override fun onResult(method: BotApiMethod<T>?, response: T) {
            cont.resume(response)
        }

        override fun onException(method: BotApiMethod<T>?, exception: Exception) {
            cont.resumeWithException(exception)
        }

        override fun onError(method: BotApiMethod<T>?, apiException: TelegramApiRequestException) {
            cont.resumeWithException(apiException)
        }
    })
}