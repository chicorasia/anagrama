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

    fun enviar(palpite: String) {
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

    fun novoJogo() {
        _round.value = 1
        _score.value = 0
    }


    private val listaDePalavras: List<String> = arrayListOf(
        "Abacate",
        "Abacaxi",
        "Abiu",
        "Abricó",
        "Abrunho",
        "Açaí",
        "Acerola",
        "Akee",
        "Alfarroba",
        "Ameixa",
        "Amêndoa",
        "Amora",
        "Ananás",
        "Anona",
        "Araçá",
        "Arando",
        "Araticum",
        "Ata",
        "Atemoia",
        "Avelã",
        "Babaco",
        "Babaçu",
        "Bacaba",
        "Bacuri",
        "Bacupari",
        "Banana",
        "Baru",
        "Bergamota",
        "Biribá",
        "Buriti",
        "Butiá",
        "Cabeludinha",
        "Cacau",
        "Cagaita",
        "Caimito",
        "Cajá",
        "Caju",
        "Calabaça",
        "Calabura",
        "Calamondin",
        "Cambucá",
        "Cambuci",
        "Camu-camu",
        "Caqui",
        "Carambola",
        "Carnaúba",
        "Castanha",
        "Castanha-do-pará",
        "Cereja",
        "Ciriguela",
        "Ciruela",
        "Coco",
        "Cranberry",
        "Cupuaçu",
        "Damasco",
        "Dekopon",
        "Dendê",
        "Dióspiro",
        "Dovyalis",
        "Durião",
        "Embaúba",
        "Embaubarana",
        "Engkala",
        "Escropari",
        "Esfregadinha",
        "Figo",
        "Framboesa",
        "Fruta-do-conde",
        "Fruta-pão",
        "Feijoa",
        "Figo-da-índia",
        "Fruta-de-cedro",
        "Fruta-de-lobo",
        "Fruta-do-milagre",
        "Fruta-de-tatu",
        "Gabiroba",
        "Glicosmis",
        "Goiaba",
        "Granadilla",
        "Gravatá",
        "Graviola",
        "Groselha",
        "Grumixama",
        "Guabiju",
        "Guabiroba",
        "Guaraná",
        "Hawthorn",
        "Heisteria",
        "Hilocéreo",
        "Ibacurupari",
        "Ilama",
        "Imbe",
        "Imbu",
        "Inajá",
        "Ingá",
        "Inharé",
        "Jabuticaba",
        "Jaca",
        "Jambo",
        "Jambolão",
        "Jamelão",
        "Jaracatiá",
        "Jatobá",
        "Jenipapo",
        "Jerivá",
        "Juá",
        "Jujuba",
        "Kiwi",
        "Kumquat",
        "Kinkan",
        "Kino",
        "Kiwano",
        "Kabosu",
        "Karité",
        "Laranja",
        "Limão",
        "Lima",
        "Lichia",
        "Longan",
        "Lucuma",
        "Lacucha",
        "Lulo",
        "Lobeira",
        "Langsat",
        "Laranja-de-pacu",
        "Mabolo",
        "Maçã",
        "Macadâmia",
        "Macaúba",
        "Mamão",
        "Mamey",
        "Mamoncillo",
        "Maná-cubiu",
        "Manga",
        "Mangaba",
        "Mangostão",
        "Maracujá",
        "Marang",
        "Marmelo",
        "Marolo",
        "Marula",
        "Massala",
        "Melancia",
        "Melão",
        "Meloa",
        "Mexerica",
        "Mirtilo",
        "Morango",
        "Murici",
        "Naranjilla",
        "Nectarina",
        "Nêspera",
        "Noni",
        "Noz",
        "Noz-pecã",
        "Noz-macadâmia",
        "Oiti",
        "Oxicoco",
        "Orangelo",
        "Pera",
        "Pêssego",
        "Pitanga",
        "Pinha",
        "Pitaia",
        "Pitomba",
        "Pitangatuba",
        "Pindaíba",
        "Pequi",
        "Pequiá",
        "Physalis",
        "Pulasan",
        "Pomelo",
        "Pupunha",
        "Puçá",
        "Patauá",
        "Pajurá",
        "Pixirica",
        "Pistache",
        "Quina",
        "Quiuí",
        "Romã",
        "Rambai",
        "Rambutão",
        "Rukam",
        "Saguaraji",
        "Salak",
        "Santol",
        "Sapota",
        "Sapoti",
        "Sapucaia",
        "Saputá",
        "Seriguela",
        "Sorvinha",
        "Tangerina",
        "Tamarindo",
        "Tâmara",
        "Toranja",
        "Tucumã",
        "Taiuva",
        "Tapiá",
        "Tarumã",
        "Tangor",
        "Tucujá",
        "Uva",
        "Umbu",
        "Uvaia",
        "Uchuva",
        "Umê",
        "Uxi",
        "Vacínio",
        "Veludo",
        "Vergamota",
        "Wampi",
        "Xixá",
        "Yamamomo",
        "Yuzu",
        "Zimbro"
    )

}