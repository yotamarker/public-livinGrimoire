import os
from LivinGrimoire23 import AbsDictionaryDB

'''
example usage code:
    db1: AbsDictionaryDB = LivinGrimoirePandaDB()
    db1.save("test1", "working1")
    db1.save("test2", "working2")
    db1.save("test2", "working3")
    print(db1.load("test1"))
    print(db1.load("test2"))
    print(db1.load("test3"))
'''


def save_to_txt(db_directory, key, value):
    with open(f'{db_directory}/{key}.txt', mode="w") as file:
        file.write(value)


def read_from_txt(db_directory, key):
    if not os.path.exists(f'{db_directory}/{key}.txt'):
        return "null"
    with open(f'{db_directory}/{key}.txt', 'r') as f:
        return f.read()


def check_data_exists(db_directory, key):
    return f'{db_directory}/{key}.txt'


class LivinGrimoirePandaDB(AbsDictionaryDB):
    def __init__(self, database_name: str = 'database'):
        super().__init__()
        self._db_name = database_name
        if not os.path.exists(self._db_name):
            os.makedirs(self._db_name)
            print(f"Directory '{self._db_name}' created successfully.")
        else:
            print(f"Directory '{self._db_name}' already exists.")

    def save(self, key: str, value: str):
        save_to_txt(self._db_name, key, value)

    def load(self, key: str) -> str:
        return read_from_txt(self._db_name, key)

    def dataExists(self, key: str) -> bool:
        return check_data_exists(self._db_name, key)
