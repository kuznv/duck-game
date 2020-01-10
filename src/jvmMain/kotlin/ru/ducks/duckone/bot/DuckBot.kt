package ru.ducks.duckone.bot

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ru.ducks.duckone.bot.adapter.exec
import kotlin.system.measureTimeMillis

class DuckBot(botOptions: DefaultBotOptions) : TelegramLongPollingBot(botOptions), CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Default

    private val botToken = System.getenv("duck_token")!!
    override fun getBotToken() = botToken

    override fun getBotUsername() = "duck_bot"

    override fun onUpdateReceived(update: Update) {

        val message = update.message
        val chat = message?.chat
        println(update)

        launch {
            println("processing $update")
            update.inlineQuery
            val callbackQuery = update.callbackQuery
            if (callbackQuery == null) {
                chat ?: return@launch
                SendMessage(chat.id, "Test").apply {
                    replyMarkup = InlineKeyboardMarkup().apply {
                        keyboard = listOf(
                            listOf(InlineKeyboardButton("test").apply { callbackData = "test" })
                        )
                    }
                }.let { exec(it) }
            } else {
                AnswerCallbackQuery().apply {
                    text = "test123"
                    callbackQueryId = callbackQuery.id
                }.let { exec(it) }
            }
        }
    }
}