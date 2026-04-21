package com.budgetnah.pro.data.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth

val supabase = createSupabaseClient(
    supabaseUrl = "https://gbnmgmyejliutfeqgajj.supabase.co",
    supabaseKey = "sb_publishable__DvJuzxSpDMpZFRFcxkX2g_P3EUONxc"
) {
    install(Auth)
}
