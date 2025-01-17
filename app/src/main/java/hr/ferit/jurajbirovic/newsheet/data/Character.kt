package hr.ferit.jurajbirovic.newsheet.data

data class Character(
    var id: String="",
    var name: String="",
    var title: String="",
    var sex: String="",
    var race: String="",
    var characterClass: String="",
    var image: String="",
    var lore: String="",
    var stats: Stats
)

data class Stats(
    var strength: Int=0,
    var defense: Int=0,
    var agility: Int=0
)
