package com.example.vscode.mobile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vscode.mobile.data.MarketplaceRepository
import com.example.vscode.mobile.data.Plugin
import com.example.vscode.mobile.ui.theme.VSCodeAccent
import com.example.vscode.mobile.ui.theme.VSCodeBackground
import com.example.vscode.mobile.ui.theme.VSCodeLine
import com.example.vscode.mobile.ui.theme.VSCodeMuted
import com.example.vscode.mobile.ui.theme.VSCodePanel
import com.example.vscode.mobile.ui.theme.VSCodeSidebar
import com.example.vscode.mobile.ui.theme.VSCodeText

@Composable
fun VSCodeMobileApp() {
    val context = LocalContext.current
    val repository = remember { MarketplaceRepository() }
    var plugins by remember { mutableStateOf<List<Plugin>>(emptyList()) }
    var selectedTab by remember { mutableStateOf("main.kt") }
    var showMarketplace by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        plugins = repository.loadPlugins(context)
    }

    val availableTabs = listOf("main.kt", "settings.gradle.kts", "plugin.json", "README.md")
    val sourceCode = listOf(
        "fun main() {",
        "    println(\"Hello VS Code Mobile\")",
        "    renderWorkspace()",
        "}",
        "",
        "val project = Project(\"mobile\")",
        "project.enableMarketplace = true"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VSCodeBackground)
    ) {
        TopBar(showMarketplace = showMarketplace, onToggleMarketplace = { showMarketplace = !showMarketplace })

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            ExplorerSidebar()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                EditorTabs(
                    tabs = availableTabs,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )

                EditorSurface(
                    fileName = selectedTab,
                    lines = sourceCode
                )
            }

            if (showMarketplace) {
                MarketplacePanel(plugins = plugins)
            }
        }
    }
}

@Composable
private fun TopBar(showMarketplace: Boolean, onToggleMarketplace: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(VSCodePanel)
            .border(width = 1.dp, color = VSCodeLine),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = VSCodeMuted,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "VS Code Mobile",
                color = VSCodeText,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Search */ }) {
                Icon(Icons.Default.Search, contentDescription = null, tint = VSCodeMuted)
            }
            IconButton(onClick = { /* Settings */ }) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = VSCodeMuted)
            }
            IconButton(onClick = onToggleMarketplace) {
                Icon(Icons.Default.CloudDownload, contentDescription = null, tint = if (showMarketplace) VSCodeAccent else VSCodeMuted)
            }
            IconButton(onClick = { /* More */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = null, tint = VSCodeMuted)
            }
        }
    }
}

@Composable
private fun ExplorerSidebar() {
    Column(
        modifier = Modifier
            .width(220.dp)
            .fillMaxHeight()
            .background(VSCodeSidebar)
            .border(width = 1.dp, color = VSCodeLine)
            .padding(12.dp)
    ) {
        Text(
            text = "EXPLORER",
            color = VSCodeMuted,
            fontSize = 12.sp,
            letterSpacing = 0.9.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val entries = listOf(
            "mobile-app",
            "src",
            "plugins",
            "README.md",
            "build.gradle.kts"
        )

        entries.forEach { name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DataObject,
                    contentDescription = null,
                    tint = VSCodeMuted,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = name, color = VSCodeText, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = VSCodeAccent),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("New file")
        }
    }
}

@Composable
private fun EditorTabs(tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(VSCodePanel)
            .border(width = 1.dp, color = VSCodeLine),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            Box(
                modifier = Modifier
                    .background(if (tab == selectedTab) VSCodeBackground else VSCodePanel)
                    .border(width = 1.dp, color = VSCodeLine)
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (tab == selectedTab) VSCodeText else VSCodeMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun EditorSurface(fileName: String, lines: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VSCodeBackground)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = fileName,
                color = VSCodeMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VSCodeBackground)
                .border(width = 1.dp, color = VSCodeLine)
                .padding(12.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(lines) { line ->
                    Text(
                        text = line,
                        color = when {
                            line.startsWith("fun") -> Color(0xFF569CD6)
                            line.contains("println") -> Color(0xFFDCDCAA)
                            line.contains("project") -> Color(0xFF4EC9B0)
                            else -> VSCodeText
                        },
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MarketplacePanel(plugins: List<Plugin>) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .background(VSCodePanel)
            .border(width = 1.dp, color = VSCodeLine)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Marketplace",
                color = VSCodeText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = VSCodeAccent),
                modifier = Modifier.height(34.dp)
            ) {
                Icon(Icons.Default.CloudDownload, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Install")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = VSCodeLine)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(plugins) { plugin ->
                PluginCard(plugin = plugin)
            }
        }
    }
}

@Composable
private fun PluginCard(plugin: Plugin) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(VSCodeSidebar, RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = VSCodeLine, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(
            text = plugin.name,
            color = VSCodeText,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            text = "${plugin.publisher} • ${plugin.category}",
            color = VSCodeMuted,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = plugin.description,
            color = VSCodeText,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "★ ${plugin.rating}", color = VSCodeYellow, fontSize = 12.sp)
            Text(text = plugin.downloads, color = VSCodeMuted, fontSize = 12.sp)
            Text(text = plugin.version, color = VSCodeGreen, fontSize = 12.sp)
        }

        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            plugin.tags.take(3).forEach { tag ->
                Text(
                    text = tag,
                    color = VSCodeText,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .background(VSCodePanel, RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (plugin.installed) VSCodePanel else VSCodeAccent,
                contentColor = VSCodeText
            ),
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            Text(text = if (plugin.installed) "Installed" else "Install")
        }
    }
}
