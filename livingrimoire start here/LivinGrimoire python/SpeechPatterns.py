import random

from AXPython import AXNeuroSama


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
