package strategy

/**
 * @author ranaaditya
 *
 * Strategy Design Pattern
 */
interface EncryptionStrategy {
    fun encryptData(plainText: String?)
}

class AesEncryptionStrategy: EncryptionStrategy {
    override fun encryptData(plainText: String?) {
        println("Encrypting data using AES algorithm")
    }
}

class RSAEncryptionStrategy: EncryptionStrategy {
    override fun encryptData(plainText: String?) {
        println("Encrypting data using RSA strategy")
    }
}

class TripleDESStrategy: EncryptionStrategy {
    override fun encryptData(plainText: String?) {
        println("Encrypting data using TripleDES strategy")
    }
}

class BlowFishEncryptionStrategy: EncryptionStrategy {
    override fun encryptData(plainText: String?) {
        println("Encrypting data using BlowFish strategy")
    }
}

class Encryptor(strategy: EncryptionStrategy) {
    private var strategy: EncryptionStrategy =strategy

    fun encrypt(plainText: String?) {
        strategy.encryptData(plainText)
    }
}

fun main() {
    val text = "hello world !"
    val encryptor = Encryptor(TripleDESStrategy())
    encryptor.encrypt(text)
}