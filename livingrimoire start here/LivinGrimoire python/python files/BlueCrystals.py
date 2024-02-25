#  for langauge skills
from skills import DiBlueCrystal


class RussianWordGems:
    def __init__(self):
        self._o1: DiBlueCrystal = DiBlueCrystal()  # private variable with type annotation
        #  jungle animals:
        map: dict[str, str] = {
            "лев": "lion",
            "тигр": "tiger",
            "ягуар": "jaguar",
            "анаконда": "anaconda",
            "слон": "elephant",
            "леопард": "leopard",
            "крокодил": "crocodile",
            "обезьяна": "monkey",
            "коала": "coala",
            "лемур": "lemur",
            "жираф": "giraffe",
            "пантера": "panther",
        }
        self._o1.addCategory(map)
        # sea creatures
        map = {
            "акула": "shark",
            "скат": "stingray",
            "кит": "whale",
            "осьминог": "octapus",
            "медуза": "jellyfish",
            "дельфин": "dolphine",
            "черепаха": "turtle",
            "краб": "crab",
            "мурена": "eel",
            "рыба": "fish",
            "креветка": "shrimp",
            "конек": "sea horse"
        }
        #  lv2
        self._o1.addCategory(map)
        # pets
        map = {
            "кошка": "cat",
            "собака": "dog",
            "хомяк": "hamster",
            "папугаи": "parrot",
            "яшерица": "lizard",
            "крыса": "rat",
            "кролик": "rabbit",
            "рыбка": "fish",
            "канарейка": "canary",
            "хорёк": "ferret",
            "котёнок": "kitty",
            "щенок": "puppy"
        }
        self._o1.addCategory(map)
        # forest animals
        map = {
            "заяц": "hare",
            "лисица": "fox",
            "барсук": "badger",
            "белка": "squirl",
            "волк": "wold",
            "ёж": "hedgehog",
            "медведь": "bear",
            "кабан": "boar",
            "бобр": "beaver",
            "енот": "raccoon",
            "олень": "deer",
            "лось": "moose"
        }
        self._o1.addCategory(map)
        # farm animals
        map = {
            "корова": "cow",
            "лошадь": "horse",
            "свинья": "pig",
            "курица": "chicken",
            "утка": "duck",
            "петух": "rooster",
            "индюк": "turkey",
            "Гусь": "goose",
            "осёл": "donkey",
            "баран": "ram",
            "овца": "sheep",
            "коза": "goat"
       }
        self._o1.addCategory(map)
        # space
        map = {
            "солнце": "sun",
            "земля": "earth",
            "звезда": "star",
            "Юпитер": "jupiter",
            "Сатурн": "saturn",
            "луна": "moon",
            "ракета": "rocket",
            "космонавт": "astronaut",
            "Марс": "mars",
            "венера": "venus",
            "Нептун": "neptune",
            "Уран": "uranus"
        }
        self._o1.addCategory(map)
        # transport
        map = {
            "машина": "car",
            "автобус": "bus",
            "самолёт": "airplane",
            "поезд": "train",
            "корабль": "ship",
            "велосипед": "bicycle",
            "трактор": "tractor",
            "такси": "taxi",
            "мотоцикл": "motorbike",
            "самосвал": "dump truck",
            "лодка": "boat",
            "экскаватор": "excavator"
        }
        self._o1.addCategory(map)
        map = {
            "скрипка": "violin",
            "рояль": "piano",
            "гитара": "gitar",
            "саксофон": "saxophone",
            "барабан": "drum",
            "бубен": "tambourine",
            "флейта": "flute",
            "волынка": "bagpipes",
            "тромбон": "trombone",
            "аккордеон": "accordion",
            "банджо": "banjo",
            "арфа": "harp"
        }
        self._o1.addCategory(map)
        # weather
        map = {
            "дождь": "rain",
            "снег": "snow",
            "метель": "blizzard",
            "ветер": "wind",
            "град": "hail",
            "холод": "cold",
            "облако": "cloud",
            "радуга": "rainbow",
            "торнадо": "tornado",
            "лёд": "ice",
            "молния": "lightning",
            "мороз": "freezing"
        }
        self._o1.addCategory(map)
        # winter
        map = {
            "снегопад": "snowfall",
            "горка": "slide",
            "санки": "sled",
            "лыжи": "skis",
            "сугроб": "snowdrift",
            "шарф": "scarf",
            "коньки": "skates",
            "хаккеи": "hackeys",
            "варежки": "mittens",
            "снегирь": "bullfinch",
            "ёлка": "christmas tree",
            "шапка": "cap"
        }
        self._o1.addCategory(map)
        # spring
        map = {
            "лужа": "puddle",
            "плащ": "cloak",
            "трава": "grass",
            "птица": "bird",
            "нарцисс": "narcissus",
            "лист": "leaf",
            "подснежник": "snowdrop",
            "грабли": "rake",
            "гнездо": "nest",
            "зонт": "umbrella",
            "цветение": "bloom",
            "бабочка": "butterfly"
        }
        self._o1.addCategory(map)
        # summer
        map = {
            "шляпа": "hat",
            "очки": "glasses",
            "море": "sea",
            "песок": "sand",
            "рыбалка": "fishing",
            "пикник": "picnic",
            "чемодан": "suitcase",
            "парк": "park",
            "Футболка": "t-shirt",
            "мороженое": "ice cream",
            "гриб": "mushroom",
            "ягода": "berry"
        }
        self._o1.addCategory(map)
        # autumn
        map = {
            "тыква": "pumpkin",
            "школа": "school",
            "туча": "cloud",
            "листопад": "leaf fall",
            "сапоги": "boots",
            "свитер": "sweater",
            "куртка": "jacket",
            "урожаи": "harvests",
            "комбаин": "harvester",
            "пшеница": "wheat",
            "мёд": "honey",
            "жёлудь": "acorn"
        }
        self._o1.addCategory(map)
        # school
        map = {
            "ученик": "student",
            "тетрадь": "notebook",
            "карандаш": "pencil",
            "глобус": "globe",
            "краски": "paints",
            "книга": "book",
            "ручка": "pen",
            "линейка": "ruler",
            "клей": "glue",
            "ножницы": "scissors",
            "портфель": "briefcase",
            "доска": "board"
        }
        self._o1.addCategory(map)
        # sport
        map = {
            "футбол": "football",
            "волейбол": "volleyball",
            "баскетбол": "basketball",
            "теннис": "tennis",
            "хоккей": "hockey",
            "бокс": "boxing",
            "бадминтон": "badminton",
            "гимнастика": "gymnastics",
            "плавание": "swimming",
            "гольф": "golf",
            "дзюдо": "juudo",
            "йога": "yoga"
        }
        self._o1.addCategory(map)
        # fruit
        map = {
            "апельсин": "orange",
            "яблоко": "apple",
            "виноград": "grapes",
            "персик": "peach",
            "ананас": "pineapple",
            "груша": "pear",
            "манго": "mango",
            "грейпфрут": "grapefruit",
            "мандарин": "mandarin",
            "банан": "banana",
            "киви": "kiwi",
            "абрикос": "apricot"
        }
        self._o1.addCategory(map)
        # vegetables
        map = {
            "картофель": "potato",
            "лук": "onion",
            "чеснок": "garlic",
            "кабачок": "zucchini",
            "баклажан": "eggplant",
            "капуста": "cabbage",
            "перец": "pepper",
            "огурец": "cucumber",
            "морковь": "carrot",
            "кукуруза": "corn",
            "редис": "radish",
            "врокколи": "broccoli"
        }
        self._o1.addCategory(map)
        # professions
        map = {
            "доктор": "doctor",
            "пилот": "pilot",
            "Водитель": "driver",
            "повар": "cook",
            "строитель": "builder",
            "почтальон": "postman",
            "учитель": "teacher",
            "продавец": "salesman",
            "парикмахер": "hairdresser",
            "полицейский": "police officer",
            "артист": "artist",
            "слесарь": "locksmith"
        }
        self._o1.addCategory(map)
        # насекомые insects
        map = {
            "жук": "bug",
            "паук": "spider",
            "комар": "mosquito",
            "муха": "fly",
            "гусеница": "caterpillar",
            "бабочка": "butterfly",
            "пчела": "bee",
            "муравей": "ant",
            "стрекоза": "dragonfly",
            "богомол": "mantis",
            "улитка": "snail",
            "кузнечик": "grasshopper"
        }
        self._o1.addCategory(map)
        # ягоды berries
        map = {
            "клубника": "strawberry",
            "малина": "raspberries",
            "вишня": "cherry",
            "крыжовник": "gooseberry",
            "смородина": "currant",
            "черника": "blueberry",
            "ежевика": "blackberry",
            "земляника": "strawberries",
            "клюква": "cranberry",
            "облепиха": "sea buckthorn",
            "рябина": "rowan",
            "шиповник": "rose hip"
        }
        self._o1.addCategory(map)
        # food
        map = {
            "хлеб": "bread",
            "суп": "soup",
            "шоколад": "chocolate",
            "пицца": "pizza",
            "яйца": "eggs",
            "каша": "porridge",
            "сыр": "cheese",
            "молоко": "milk",
            "печенье": "cookie",
            "пирог": "pie",
            "масло": "butter",
            "салат": "salad"
        }
        self._o1.addCategory(map)
        self._o1.setLvUpAt(9)

    def retSkill(self) -> DiBlueCrystal:  # method with return type annotation
        return self._o1
