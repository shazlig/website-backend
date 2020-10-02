package utils

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

const val secretKey: String = "72692daccd27d2ccec30cfe47c1bac0f60454a611f38cd87161937f2f341223f"

fun getSha256Signature(data: String): String{
    val digest = MessageDigest.getInstance("SHA-256")
    val encodedhash = digest.digest(
        data.toByteArray(StandardCharsets.UTF_8)
    )
    return bytesToHex(encodedhash)
}

fun bytesToHex(bytes: ByteArray): String {
    val hexArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    val hexChars = CharArray(bytes.size * 2)
    var v: Int
    for (j in bytes.indices) {
        v = bytes[j].toInt() and 0xFF
        hexChars[j * 2] = hexArray[v.ushr(4)]
        hexChars[j * 2 + 1] = hexArray[v and 0x0F]
    }
    return String(hexChars)
}

fun generateHashKey(message: String): String{
    val secretKey = getSha256Signature(secretKey)
    val hasher = Mac.getInstance("HmacSHA256")
    hasher.init(SecretKeySpec(secretKey.toByteArray(), "HmacSHA256"))
    return Base64.getEncoder().encodeToString(hasher.doFinal(message.toByteArray()))
}

fun isValidPassword(password: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    pattern = Pattern.compile(passwordPattern)
    matcher = pattern.matcher(password)
    return matcher.matches()
}