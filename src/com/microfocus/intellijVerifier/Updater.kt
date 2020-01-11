package com.microfocus.intellijVerifier

import java.io.IOException
import java.net.URL

class Updater {
    @Throws(IOException::class)
    fun update() {
        val version = getLastVersion()
        val commands = listOf("curl", "-L", "--output", "verifier-all.jar", "https://dl.bintray.com/jetbrains/intellij-plugin-service/org/jetbrains/intellij/plugins/verifier-cli/$version/verifier-cli-$version-all.jar")
        CommandLineExecutor("d:/").runAndReadOutput(commands)
    }

    @Throws(IOException::class)
    private fun getLastVersion() : String {
        val con = URL(verifierPath + "_latestVersion").openConnection()
        con.connect()
        val `is` = con.getInputStream()
        val version = con.url.toString().substring(verifierPath.length)
        `is`.close()
        return version
    }
}