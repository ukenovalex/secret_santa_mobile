package auth.kvalut

import com.liftric.kvault.KVault
import kvault.KEYS.AUTH_TOKEN

class KVaultAuthRemoteDataSource(private val store: KVault) {
    val isTokenExist
    get() = store.existsObject(AUTH_TOKEN)

    fun saveToken(token: String) {
        store.set(AUTH_TOKEN, token)
    }
    fun clear() = store.clear()
}