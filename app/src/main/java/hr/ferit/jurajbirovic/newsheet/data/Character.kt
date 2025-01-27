package hr.ferit.jurajbirovic.newsheet.data


data class Character(
    val id: String = "",
    val name: String = "",
    val title: String = "",
    val sex: String = "",
    val race: String = "",
    val characterClass: String = "",
    val lore: String = "",
    val stats: Stats = Stats()
)

data class Stats(
    val strength: Int = 0,
    val defense: Int = 0,
    val agility: Int = 0
)