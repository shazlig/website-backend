package data.libs.session

import io.ktor.sessions.*
import io.ktor.utils.io.*

class RedisSessionStorage: SessionStorage {

    override suspend fun invalidate(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun <R> read(id: String, consumer: suspend (ByteReadChannel) -> R): R {
        TODO("Not yet implemented")
    }

    override suspend fun write(id: String, provider: suspend (ByteWriteChannel) -> Unit) {
        TODO("Not yet implemented")
    }
}