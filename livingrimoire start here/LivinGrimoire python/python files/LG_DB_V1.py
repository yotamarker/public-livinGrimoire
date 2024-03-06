import pandas as pd

'''
example usage code:
from LG_DB_V1 import *  # database
    # Save data
    save_to_csv('my_database.csv', 'key', 'value')
    save_to_csv('my_database.csv', 'apple', 'fruit')
    save_to_csv('my_database.csv', 'carrot', 'vegetable')

    # Read data
    print(read_from_csv('my_database.csv', 'apple'))  # Output: 'fruit'
    print(read_from_csv('my_database.csv', 'carrot'))  # Output: 'vegetable'
    print(read_from_csv('my_database.csv', 'banana'))  # Output: None

    # Check if data exists
    print(check_data_exists('my_database.csv', 'apple'))  # Output: True
    print(check_data_exists('my_database.csv', 'banana'))  # Output: False
'''


def save_to_csv(filename, key, value):
    # Create a DataFrame with the given key and value
    df = pd.DataFrame({'key': [key], 'value': [value]})
    # Append to the existing CSV file or create a new one
    df.to_csv(filename, mode='a', header=False, index=False)


def read_from_csv(filename, key):
    try:
        df = pd.read_csv(filename)
        value = df.loc[df['key'] == key, 'value'].iloc[0]
        return value
    except (FileNotFoundError, IndexError):
        return None


def check_data_exists(filename, key):
    # Read the entire CSV file into a DataFrame
    df = pd.read_csv(filename)
    return key in df['key'].values
