package abstractfactory

interface Chat {
    fun getChannels(): List<Channel>
    fun getMessages(channelId: Long): List<Message>
}

data class Channel(
    val id: Long,
    val name: String,
    val users: List<User>
)

data class Message(
    val id: Long,
    val text: String
)

sealed class User {
    object Self : User()
    class Other(val name: String) : User()
}

class SDKChatFactory : ChatFactory() {
    override fun getChat() = TODO()
}

class FakeChatFactory : ChatFactory() {
    override fun getChat() = FakeChat()
}

class FakeChat : Chat {
    override fun getChannels(): List<Channel> {
        return listOf(
            Channel(
                id = 1,
                name = "First Channel",
                users = listOf(User.Self, User.Other("Tom"))
            )
        )
    }

    override fun getMessages(channelId: Long): List<Message> {
        return listOf(
            Message(id = 1, text = "Hello"),
            Message(id = 2, text = "How are you?")
        )
    }
}

class SDKChat : Chat {
    override fun getChannels(): List<Channel> {
        TODO("Not yet implemented")
    }

    override fun getMessages(channelId: Long): List<Message> {
        TODO("Not yet implemented")
    }
}

abstract class ChatFactory {
    abstract fun getChat(): Chat

    companion object {
        inline fun <reified T : Chat> create(): ChatFactory =
            when (T::class) {
                FakeChat::class -> FakeChatFactory()
                SDKChat::class -> SDKChatFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

