package com.tourist.app.data

data class Destination(
    val id: Int,
    val name: String,
    val location: String,
    val rating: Float,
    val imageColor: Long,  // Color used as placeholder since no real images
    val category: String,
    val bestTime: String,
    val duration: String,
    val description: String,
    val isFeatured: Boolean = false,
    val emoji: String = "🏛️"
)

data class ChatMessage(
    val id: Int,
    val text: String,
    val isUser: Boolean,
    val timestamp: String
)

data class TripPlan(
    val destination: String = "",
    val days: String = "",
    val travelers: String = "",
    val budget: String = "",
    val interests: List<String> = emptyList()
)

object DummyData {

    val featuredDestinations = listOf(
        Destination(
            id = 1,
            name = "Hampi",
            location = "Karnataka, India",
            rating = 4.8f,
            imageColor = 0xFFE8A87C,
            category = "Historical",
            bestTime = "Oct – Feb",
            duration = "2-3 Days",
            description = "Hampi, a UNESCO World Heritage Site, was once the capital of the Vijayanagara Empire. The ruins spread across a surreal boulder-strewn landscape with the Tungabhadra River flowing through. Ancient temples, market streets, royal enclosures, and elephant stables tell tales of a glorious past. The Virupaksha Temple is still an active place of worship, while Vittala Temple's stone chariot is an iconic image of India.",
            isFeatured = true,
            emoji = "🏛️"
        ),
        Destination(
            id = 2,
            name = "Munnar",
            location = "Kerala, India",
            rating = 4.7f,
            imageColor = 0xFF4CAF50,
            category = "Nature",
            bestTime = "Sep – Mar",
            duration = "3-4 Days",
            description = "Munnar is a breathtaking hill station nestled in the Western Ghats of Kerala, famous for its lush tea plantations, misty mountains, and cool climate. The Eravikulam National Park is home to the endangered Nilgiri Tahr. Tea Museum offers insights into tea processing. Anamudi Peak, the highest in South India, attracts trekkers. The region is also known for spice plantations and stunning waterfalls.",
            isFeatured = true,
            emoji = "🌿"
        ),
        Destination(
            id = 3,
            name = "Varanasi Ghats",
            location = "Uttar Pradesh, India",
            rating = 4.9f,
            imageColor = 0xFFFF9800,
            category = "Religious",
            bestTime = "Nov – Mar",
            duration = "2-3 Days",
            description = "Varanasi, one of the world's oldest continuously inhabited cities, sits on the banks of the sacred Ganges. The ghats — a series of steps leading to the river — are the soul of this ancient city. Witness the mesmerizing Ganga Aarti at Dashashwamedh Ghat, attend sunrise boat rides, and explore the labyrinthine lanes filled with temples, shrines, and the fragrance of incense. Sarnath, where Buddha gave his first sermon, is nearby.",
            isFeatured = false,
            emoji = "🕯️"
        ),
        Destination(
            id = 4,
            name = "Ranthambore",
            location = "Rajasthan, India",
            rating = 4.6f,
            imageColor = 0xFF8D6E63,
            category = "Nature",
            bestTime = "Oct – Apr",
            duration = "2-3 Days",
            description = "Ranthambore National Park is one of the best places in India to spot the majestic Bengal tiger in the wild. The park also features a stunning 10th-century fort, a UNESCO World Heritage Site. Ancient temples dot the landscape, and the park's lakes attract a variety of birds and crocodiles. Jeep and canter safaris offer thrilling wildlife encounters amidst a dramatic rocky terrain.",
            isFeatured = false,
            emoji = "🐯"
        ),
        Destination(
            id = 5,
            name = "Jaisalmer",
            location = "Rajasthan, India",
            rating = 4.7f,
            imageColor = 0xFFFFD54F,
            category = "Historical",
            bestTime = "Nov – Feb",
            duration = "3-4 Days",
            description = "Jaisalmer, the Golden City, rises from the heart of the Thar Desert like a mirage. The magnificent Jaisalmer Fort is a living fort — one of the largest in the world — with hotels, restaurants, and shops within its walls. Havelis with intricate sandstone carvings, camel safaris over golden dunes, and the Sam Sand Dunes at sunset make it an unforgettable experience.",
            isFeatured = false,
            emoji = "🏰"
        ),
        Destination(
            id = 6,
            name = "Tirupati",
            location = "Andhra Pradesh, India",
            rating = 4.9f,
            imageColor = 0xFF9C27B0,
            category = "Religious",
            bestTime = "Sep – Feb",
            duration = "1-2 Days",
            description = "Tirupati is home to the Sri Venkateswara Temple atop Tirumala Hills, one of the most visited religious pilgrimage sites in the world. Millions of devotees flock here annually. The temple's architecture is a superb example of Dravidian style, adorned with gold. The Prasadam (laddu) is world-famous. The journey through seven hills is itself a spiritual experience.",
            isFeatured = false,
            emoji = "🙏"
        )
    )

    val popularDestinations get() = featuredDestinations.filter { !it.isFeatured }

    val sampleChatMessages = listOf(
        ChatMessage(1, "Hello! I'm Voyage AI, your intelligent travel companion. Ask me anything about your next adventure! ✈️", false, "10:00 AM"),
        ChatMessage(2, "What are the best places to visit in Rajasthan?", true, "10:01 AM"),
        ChatMessage(3, "Rajasthan is a treasure trove! 🏰 Here are the must-visits:\n\n• **Jaipur** – The Pink City with Amber Fort & Hawa Mahal\n• **Jaisalmer** – Golden City with desert dunes\n• **Udaipur** – City of Lakes, incredibly romantic\n• **Jodhpur** – The Blue City with Mehrangarh Fort\n• **Ranthambore** – Tiger reserve & historical fort\n\nBest time to visit is October to March. Want me to plan an itinerary?", false, "10:01 AM")
    )

    val sampleQuestions = listOf(
        "🗺️ Plan a 5-day trip to Kerala",
        "🌤️ Best time to visit Ladakh?",
        "💰 Budget travel tips for Goa",
        "🍽️ Must-try foods in Tamil Nadu",
        "🏔️ Trekking spots in Himachal Pradesh",
        "🎭 Cultural festivals in India 2025"
    )

    val categories = listOf("All", "Nature", "Historical", "Religious")

    val interestOptions = listOf(
        "Adventure", "Culture", "Food", "Nature",
        "History", "Wellness", "Photography", "Shopping"
    )
}
