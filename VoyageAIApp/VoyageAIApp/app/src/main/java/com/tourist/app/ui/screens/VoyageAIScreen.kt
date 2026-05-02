package com.tourist.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourist.app.data.ChatMessage
import com.tourist.app.data.DummyData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VoyageAIScreen() {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var inputText by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }

    val messages = remember {
        mutableStateListOf<ChatMessage>().apply {
            addAll(DummyData.sampleChatMessages)
        }
    }

    val sampleResponses = listOf(
        "That's a wonderful destination! Here are some top highlights:\n\n🌟 Must-see attractions with local insights\n🍛 Best local food spots to try\n🏨 Recommended stay areas\n💡 Pro tip: Visit on weekdays to avoid crowds!",
        "Great choice! For the best experience:\n\n📅 Best months: October to February\n🎒 Pack light cottons for the climate\n💰 Budget estimate: ₹2,000-5,000 per day\n🚌 Local transport options are affordable!",
        "Here's what I recommend for your itinerary:\n\nDay 1: Arrival & local orientation 🗺️\nDay 2: Main historical sites 🏛️\nDay 3: Nature & scenic spots 🌿\nDay 4: Shopping & departure prep 🛍️\n\nWant me to elaborate on any day?"
    )

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = ChatMessage(
            id = messages.size + 1,
            text = text,
            isUser = true,
            timestamp = "Now"
        )
        messages.add(userMsg)
        inputText = ""
        isTyping = true

        coroutineScope.launch {
            delay(1200)
            isTyping = false
            val aiMsg = ChatMessage(
                id = messages.size + 1,
                text = sampleResponses.random(),
                isUser = false,
                timestamp = "Now"
            )
            messages.add(aiMsg)
            delay(100)
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                )
                .padding(top = 52.dp, start = 20.dp, end = 20.dp, bottom = 18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.SmartToy, null, tint = Color.White, modifier = Modifier.size(26.dp))
                }
                Column {
                    Text(
                        "Voyage AI",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50))
                        )
                        Text(
                            "Always here to help",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        // Sample questions (only if minimal messages)
        if (messages.size <= 3) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Try asking:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(DummyData.sampleQuestions) { question ->
                        SampleQuestionChip(question = question, onClick = { sendMessage(question) })
                    }
                }
            }
        }

        // Messages
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(messages) { msg ->
                ChatBubble(message = msg)
            }
            if (isTyping) {
                item {
                    TypingIndicator()
                }
            }
            item { Spacer(Modifier.height(8.dp)) }
        }

        // Input area
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 12.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    BasicTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 13.dp),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        maxLines = 4,
                        decorationBox = { inner ->
                            if (inputText.isEmpty()) {
                                Text(
                                    "Ask Voyage AI anything...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                )
                            }
                            inner()
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            if (inputText.isNotBlank())
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                        )
                        .clickable { sendMessage(inputText) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Send,
                        "Send",
                        tint = if (inputText.isNotBlank()) Color.White else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isUser) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🤖", fontSize = 16.sp)
            }
            Spacer(Modifier.width(8.dp))
        }
        Column(
            horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 18.dp,
                    topEnd = 18.dp,
                    bottomStart = if (message.isUser) 18.dp else 4.dp,
                    bottomEnd = if (message.isUser) 4.dp else 18.dp
                ),
                color = if (message.isUser)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surface,
                shadowElevation = 2.dp
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (message.isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(12.dp),
                    lineHeight = 20.sp
                )
            }
            Text(
                text = message.timestamp,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.padding(top = 3.dp, start = 4.dp, end = 4.dp)
            )
        }
        if (message.isUser) {
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text("👤", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun TypingIndicator() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text("🤖", fontSize = 16.sp)
        }
        Surface(
            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 4.dp, bottomEnd = 18.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Composable
fun SampleQuestionChip(question: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = null
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp)
        )
    }
}
