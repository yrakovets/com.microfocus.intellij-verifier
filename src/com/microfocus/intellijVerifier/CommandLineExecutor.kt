package com.microfocus.intellijVerifier

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.logging.Logger

class CommandLineExecutor (private val workingDirectory : String) {
    fun runAndReadOutput(command: List<String>) : String {
        Logger.getLogger("CommandLineExecutor").info("Commands: $command")
        val pb = ProcessBuilder(command)
        pb.directory(File(workingDirectory))
        try {
            val p = pb.start()
            val isr = InputStreamReader(p.inputStream)
            val stringBuilder = StringBuilder()
            var line: String?
            BufferedReader(isr).use { bufferedReader ->
                while (bufferedReader.readLine().also { line = it } != null) {
                    if (stringBuilder.isNotEmpty()) {
                        stringBuilder.append(System.lineSeparator())
                    }
                    stringBuilder.append(line)
                }
            }
            Logger.getLogger("CommandLineExecutor").info("Result: $stringBuilder")
            return stringBuilder.toString()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return ""

    }
}