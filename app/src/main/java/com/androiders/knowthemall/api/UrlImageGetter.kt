package com.androiders.knowthemall.api

class UrlImageGetter {

    private val hasPic = listOf(
        10061, 10085, 10116, 10121, 10122, 10130, 10131, 10132, 10133, 10134, 10135, 10137,
        10138, 10139, 10140, 10141, 10142, 10143, 10145, 10147, 10158, 10159, 10178, 10181,
        10182, 10183, 10187, 10190, 10192
    )

    fun getUrl(id: Int): String {
        if (id <= 958 || !hasPic.contains(id)) {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

        } else if (id in 10061..10147 || id == 10178 || id == 10190) {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        } else if (id == 10158)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/25-starter.png"
        else if (id == 10159)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/133-starter.png"
        else if (id == 10181)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/718-10.png"
        else if (id == 10182)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/845-gulping.png"
        else if (id == 10183)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/845-gorging.png"
        else if (id == 10187)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/877-hangry.png"
        else if (id == 10192)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/893-dada.png"
        else
            return ""
    }


}