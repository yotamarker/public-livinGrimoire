import random

import nltk

from AXPython import AXNeuroSama
from nltk.corpus import wordnet
from textblob import TextBlob  # emotion detection


class nltk_beefUp:
    def __init__(self):
        nltk.download('wordnet')  # for synonims function

    @staticmethod
    def replace_synonyms(text):
        words = text.split()
        synonyms = []
        for word in words:
            syns = wordnet.synsets(word)
            if syns:
                synonyms.append(syns[0].lemmas()[0].name())
            else:
                synonyms.append(word)
        return ' '.join(synonyms)


def detect_emotion(text):
    blob = TextBlob(text)
    if blob.sentiment.polarity > 0:
        return "Positive"
    elif blob.sentiment.polarity < 0:
        return "Negative"
    else:
        return "Neutral"


def chi_speech_pattern(input_string):
    # Split the input string into words
    words = input_string.split()

    # If the string is not empty, repeat the last word
    if words:
        last_word = words[-1]
        transformed_string = input_string + ' ' + last_word
    else:
        transformed_string = input_string

    return transformed_string


def uwu_converter(text):
    text = text.replace('r', 'w')
    text = text.replace('l', 'w')
    text = text.replace('R', 'W')
    text = text.replace('L', 'W')
    return text


def g_dropping_dialect(input_string):
    # Replace 'ing' with 'in''
    input_string = input_string.replace('ing ', 'in\' ')

    return input_string


def girly_speak(sentence):
    words = sentence.split()
    girly_words = []
    cute_phrases = ["teehee", "oh my goshies", "like, totally", "for sure", "super duper"]

    for word in words:
        if len(word) > 3 and word[-1] not in ['a', 'e', 'i', 'o', 'u', 'y']:
            girly_word = word + "ies"
        else:
            girly_word = word
        girly_words.append(girly_word)

    girly_sentence = " ".join(girly_words)

    if random.random() < 0.2:
        girly_sentence += ", " + random.choice(cute_phrases)

    return girly_sentence


def unrefined_speech_pattern(input_string):
    # Replace common words with their childish equivalents
    replacements = {
        ' I ': ' me ',
        ' my ': ' me\'s ',
        ' am ': ' is ',
        ' are ': ' is ',
        ' you ': ' yoo ',
        ' the ': ' da ',
        ' and ': ' an\' ',
        ' to ': ' ta '
    }

    for word, replacement in replacements.items():
        input_string = input_string.replace(word, replacement)

    # Add a playful "~" at the end of sentences
    input_string = input_string + '~'

    return input_string


def add_comrade(sentence):
    if random.randint(1, 4) == 1:
        return sentence + ' comrade'
    else:
        return sentence


def neuro_sama(sentence: str):
    return AXNeuroSama(3).decorate(sentence)


# ;) kreng was here

def slav_potato_speech(input_text):
    # Common phrases Slav Potato might use
    potato_phrases = [
        "Da, ", "Comrade, ", "Babushka, ", "Vodka, ", "Is good, yes? ", "Ah, "
    ]

    # Replace certain words with more 'Slavic' sounding ones
    replacements = {
        'the': 'ze',
        'th': 'z',
        'w': 'v',
        'r': 'rr',
        'l': 'l',
        'is': 'iz',
        'and': 'und',
        'my': 'moy',
        'you': 'yu',
        'your': 'yur'
    }

    # Split the input text into words
    words = input_text.split()

    # Apply replacements to each word
    transformed_words = []
    for word in words:
        for key, value in replacements.items():
            word = word.replace(key, value)
        transformed_words.append(word)

    # Reconstruct the sentence
    transformed_text = ' '.join(transformed_words)

    # Randomly insert Slav Potato phrases
    final_text = random.choice(potato_phrases) + transformed_text

    return final_text


class SpeechChanger:
    def __init__(self):
        self.mode: int = -1

    def addPattern(self, ear: str) -> str:
        match self.mode:
            case 0:
                return slav_potato_speech(ear)
            case 1:
                return chi_speech_pattern(ear)
            case 2:
                return uwu_converter(ear)
            case 3:
                return neuro_sama(ear)
            case 4:
                return unrefined_speech_pattern(ear)
            case 5:
                return girly_speak(ear)
            case 6:
                return g_dropping_dialect(ear)
        return ear
