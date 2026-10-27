package com.example.vscode.mobile.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class MarketplaceRepository {
    fun loadPlugins(context: Context): List<Plugin> {
        return try {
            val json = context.assets.open("plugins.json").bufferedReader().use { it.readText() }
            val array = JSONArray(json)
            buildList {
                for (index in 0 until array.length()) {
                    val item = array.getJSONObject(index)
                    add(item.toPlugin())
                }
            }
        } catch (_: Exception) {
            demoPlugins()
        }
    }

    private fun JSONObject.toPlugin(): Plugin {
        val tags = getJSONArray("tags").let { array ->
            buildList {
                for (index in 0 until array.length()) {
                    add(array.getString(index))
                }
            }
        }

        return Plugin(
            id = getString("id"),
            name = getString("name"),
            publisher = getString("publisher"),
            description = getString("description"),
            version = getString("version"),
            category = getString("category"),
            downloads = getString("downloads"),
            rating = getDouble("rating").toFloat(),
            tags = tags,
            installed = getBoolean("installed")
        )
    }

    private fun demoPlugins(): List<Plugin> = listOf(
        Plugin(
            id = "ms-python.python",
            name = "Python",
            publisher = "Microsoft",
            description = "Support for Python language, linting, debugging and virtual envs.",
            version = "2024.12.1",
            category = "Languages",
            downloads = "8.9M",
            rating = 4.8f,
            tags = listOf("python", "debug", "lint"),
            installed = true
        ),
        Plugin(
            id = "github.vscode-pull-request-github",
            name = "GitHub Pull Requests",
            publisher = "GitHub",
            description = "Manage PRs directly inside the IDE from a mobile-friendly interface.",
            version = "0.120.0",
            category = "Git",
            downloads = "6.4M",
            rating = 4.7f,
            tags = listOf("github", "pull requests"),
            installed = false
        ),
        Plugin(
            id = "esbenp.prettier-vscode",
            name = "Prettier",
            publisher = "Prettier",
            description = "Auto formatting for JavaScript, TypeScript and markup files.",
            version = "10.4.0",
            category = "Formatters",
            downloads = "14.1M",
            rating = 4.9f,
            tags = listOf("format", "javascript"),
            installed = false
        ),
        Plugin(
            id = "dbaeumer.vscode-eslint",
            name = "ESLint",
            publisher = "Microsoft",
            description = "Real-time linting for JavaScript and TypeScript projects.",
            version = "3.3.0",
            category = "Linters",
            downloads = "12.7M",
            rating = 4.7f,
            tags = listOf("eslint", "lint"),
            installed = true
        )
    )
}