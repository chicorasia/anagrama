package br.com.chicorialabs.anagrama.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


class MainViewModel : ViewModel() {

    private val _segredo = MutableLiveData<String>()
    val segredo: LiveData<String>
        get() = _segredo

    private val _desafio = MutableLiveData<String>()
    val desafio: LiveData<String>
        get() = _desafio

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _round = MutableLiveData<Int>()
    val round: LiveData<Int>
        get() = _round

//    Uma variável para disparar a navegação para a tela de Game Over
    private val _navegaParaGameOver = MutableLiveData<Boolean?>()
    val navegaParaGameOver: LiveData<Boolean?>
        get() = _navegaParaGameOver

    val palpite = MutableLiveData<String>()

    val acerto = MutableLiveData<Boolean?>(null)

    init {
        _score.value = 0
        _round.value = 1
    }

    fun criaSegredo() {
        val palavraSecreta = sorteiaPalavra(listaDePalavras)
        _segredo.value = palavraSecreta
        embaralhaPalavra(palavraSecreta)
    }

    fun embaralhaPalavra(palavra: String) {
        val letras = arrayListOf<Char>()
        palavra.forEach {
            letras.add(it)
        }
        letras.shuffle()
        var palavraEmbaralhada: String = ""
        for (l in letras) {
            palavraEmbaralhada += l
        }
        _desafio.value = palavraEmbaralhada.toUpperCase()

    }

    fun sorteiaPalavra(lista: List<String>) : String {
        val idx = Random.nextInt(lista.indices)
        return lista[idx]
    }

    fun testar(palpite: String) {
        if (palpite.equals(segredo.value, ignoreCase = true)){
            acerto.value = true
            var scoreAtual: Int = score.value ?: 0
            scoreAtual++
            _score.value = scoreAtual
        } else {
            acerto.value = false
        }
    }

    fun avancaRound(){
        var round = _round.value ?: 0
        round++
        _round.value = round
    }

    fun jogoEncerrado() {
        _navegaParaGameOver.value = true
    }

    fun navegouParaGameOver(){
        _navegaParaGameOver.value = null
    }

    fun enviarPalpite(){
        if (round.value == 10) {
            jogoEncerrado()
        }
        if (round.value!! <= 9) {
            testar(palpite.value.toString())
            avancaRound()
            criaSegredo()
        }
    }


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

}