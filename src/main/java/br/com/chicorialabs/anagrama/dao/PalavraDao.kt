package br.com.chicorialabs.anagrama.dao

import java.util.*

class PalavraDao {

    private val listaDePalavras: List<String> = arrayListOf(
        "Abacate",
        "Abacaxi",
        "Abiu",
        "Abrunho",
        "Acerola",
        "Akee",
        "Alfarroba",
        "Ameixa",
        "Amora",
        "Anona",
        "Arando",
        "Araticum",
        "Atemoia",
        "Bacuri",
        "Bacupari",
        "Banana",
        "Bergamota",
        "Buriti",
        "Cacau",
        "Caju",
        "Calabura",
        "Calamondin",
        "Cambuci",
        "Camu-camu",
        "Caqui",
        "Carambola",
        "Castanha",
        "Cereja",
        "Ciruela",
        "Coco",
        "Cranberry",
        "Damasco",
        "Dekopon",
        "Embaubarana",
        "Escropari",
        "Esfregadinha",
        "Figo",
        "Framboesa",
        "Fruta-do-conde",
        "Feijoa",
        "Fruta-de-cedro",
        "Fruta-de-lobo",
        "Fruta-do-milagre",
        "Fruta-de-tatu",
        "Gabiroba",
        "Glicosmis",
        "Goiaba",
        "Granadilla",
        "Graviola",
        "Groselha",
        "Grumixama",
        "Guabiju",
        "Guabiroba",
        "Heisteria",
        "Ibacurupari",
        "Ilama",
        "Imbe",
        "Imbu",
        "Jabuticaba",
        "Jaca",
        "Jambo",
        "Jenipapo",
        "Jujuba",
        "Kiwi",
        "Kumquat",
        "Kinkan",
        "Kiwano",
        "Kabosu",
        "Laranja",
        "Lima",
        "Lichia",
        "Longan",
        "Lucuma",
        "Lacucha",
        "Lulo",
        "Lobeira",
        "Langsat",
        "Mabolo",
        "Mamey",
        "Mamoncillo",
        "Manga",
        "Mangaba",
        "Maracujá",
        "Marang",
        "Marmelo",
        "Marolo",
        "Marula",
        "Massala",
        "Melancia",
        "Meloa",
        "Mexerica",
        "Mirtilo",
        "Morango",
        "Murici",
        "Naranjilla",
        "Nectarina",
        "Noni",
        "Noz",
        "Oiti",
        "Oxicoco",
        "Orangelo",
        "Pera",
        "Pitanga",
        "Pinha",
        "Pitaia",
        "Pitomba",
        "Pitangatuba",
        "Pindaíba",
        "Pequi",
        "Physalis",
        "Pulasan",
        "Pomelo",
        "Pupunha",
        "Pixirica",
        "Pistache",
        "Quina",
        "Quiuí",
        "Rambai",
        "Rukam",
        "Saguaraji",
        "Salak",
        "Santol",
        "Sapota",
        "Sapoti",
        "Sapucaia",
        "Seriguela",
        "Sorvinha",
        "Tangerina",
        "Tamarindo",
        "Toranja",
        "Taiuva",
        "Tangor",
        "Uva",
        "Umbu",
        "Uvaia",
        "Uchuva",
        "Uxi",
        "Veludo",
        "Wampi",
        "Yamamomo",
        "Yuzu",
        "Zimbro"
    )

    fun getLista(): List<String> = Collections.unmodifiableList(listaDePalavras)
}