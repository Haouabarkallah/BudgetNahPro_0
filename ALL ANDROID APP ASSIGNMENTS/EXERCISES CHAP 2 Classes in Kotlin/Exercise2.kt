// Exercise 2 : Model network request state with sealed class

// Sealed class pour représenter l'état d'une requête réseau
sealed class NetworkState {

    // État de chargement
    object Loading : NetworkState()

    // État de succès avec des données
    data class Success(val data: String) : NetworkState()

    // État d'erreur avec un message
    data class Error(val message: String) : NetworkState()
}


// Fonction qui traite l'état
fun handleState(state: NetworkState) {

    when (state) {

        is NetworkState.Loading ->
            println("Loading data...")

        is NetworkState.Success ->
            println("Success: ${state.data}")

        is NetworkState.Error ->
            println("Error: ${state.message}")
    }
}

// Fonction principale
fun main() {

    val states = listOf(
        NetworkState.Loading,
        NetworkState.Success("User data loaded"),
        NetworkState.Error("Network timeout")
    )

    // Parcourir la liste et traiter chaque état
    states.forEach {
        handleState(it)
    }
}