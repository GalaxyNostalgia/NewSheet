package hr.ferit.jurajbirovic.newsheet.data

import java.util.UUID

data class Character(
    val id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var title: String = "",
    var sex: String = "",
    var race: String = "",
    var characterClass: String = "",
    var image: String = "",
    var lore: String = "",
    var stats: Stats = Stats()
)

data class Stats(
    var strength: Int = 0,
    var defense: Int = 0,
    var agility: Int = 0
) {
    init {
        require(strength >= 0) { "Strength cannot be negative" }
        require(defense >= 0) { "Defense cannot be negative" }
        require(agility >= 0) { "Agility cannot be negative" }
    }
}
