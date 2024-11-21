from AXPython import AXCmdBreaker, RegexUtil, DrawRnd
from LivinGrimoire23 import Brain, Skill
import webbrowser
import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse
from typing import List
import random

exclusions = ['google.com', 'bing.com', 'facebook.com', 'youtube.com']  # sites to exclude from link scouting
site_seeds = ['https://www.yotamarker.com/']  # site you want to scout for internal/external links
site_fuel: list[str] = ['', 'yotamarker', 'coding', 'java', 'python', 'cplusplus', 'swift']  # lists used for domain guessing
site_fuel2: list[str] = ['', 'forum', 'haven']
site_endings = ['.com', '.org', '.net', '.io', '.co', '.edu', '.gov', '.info', '.biz', '.moe', 'ninja']  # domain endings for URL guessing


def check_codephrase(codephrase: str, s: str) -> str:
    if s.startswith(codephrase):
        return s[len(codephrase):]
    return s


def crawl_web(url: str) -> List[str]:
    response = requests.get(url)
    response.raise_for_status()  # Check for request errors
    soup = BeautifulSoup(response.content, 'html.parser')
    photo_extensions = ('.jpg', '.jpeg', '.png', '.gif', '.bmp', '.svg', '.webp')
    links = [urljoin(url, a['href']) for a in soup.find_all('a', href=True) if
             not a['href'].startswith('javascript:') and
             not a['href'].lower().endswith(photo_extensions) and
             not a['href'].lower().endswith('.xml') and
             'account' not in a['href']]
    return links


def filter_mainstream_sites(links: List[str], seed_sites) -> List[str]:
    # filter out exclusion sites, and seed domain URLs
    seed_domains = [urlparse(seed_site).netloc for seed_site in seed_sites]
    return list(set([link for link in links if
                     not any(mainstream_site in link for mainstream_site in exclusions) and not any(
                         seed_domain in link for seed_domain in seed_domains)]))


def filter_externals(links: List[str], seed_sites) -> List[str]:
    # filters out exclution sites, and external sites
    seed_domains = [urlparse(seed_site).netloc for seed_site in seed_sites]
    return list(set([link for link in links if
                     not any(mainstream_site in link for mainstream_site in exclusions) and any(
                         seed_domain in link for seed_domain in seed_domains)]))


def search_engine(*seed_sites: str) -> list[str]:
    # get external links of seed URLs
    all_links = []
    for seed_site in seed_sites:
        links = crawl_web(seed_site)
        filtered_links = filter_mainstream_sites(links, seed_sites)
        all_links.extend(filtered_links)
    return all_links


def inflate_links(*seed_sites: str) -> list[str]:
    # get seed site deep links
    all_links = []
    for seed_site in seed_sites:
        links = crawl_web(seed_site)
        filtered_links = filter_externals(links, seed_sites)
        all_links.extend(filtered_links)
    return all_links


class Scouter:
    @staticmethod
    def scout():
        # get external links of seed sites
        results = search_engine(*random.sample(site_seeds, 1))
        for site1 in results:
            print(site1)

    @staticmethod
    def getDeepLinks():
        # get internal seed site links
        results = inflate_links(*random.sample(site_seeds, 1))
        for site1 in results:
            print(site1)

    @staticmethod
    def scoute_address(url: str):
        # get external links for URL
        results = search_engine(url)
        for site1 in results:
            print(site1)

    @staticmethod
    def inflate_address(url: str):
        # get internal links for URL
        results = inflate_links(url)
        for site1 in results:
            print(site1)

    @staticmethod
    def get_random_combination(strings: List[str], n: int) -> str:
        if n > len(strings):
            return "Not enough items to choose from"
        random_items = random.sample(strings, n)
        return ''.join(random_items)

    @staticmethod
    def concat_with_random_url_ending(base_str: str) -> str:
        typical_endings = ['.com', '.org', '.net', '.io', '.co', '.edu', '.gov', '.info', '.biz']
        random_ending = random.choice(typical_endings)
        return f"{base_str}{random_ending}"

    @staticmethod
    def generateRND_URL(comboFuel: list[str], maxCombos: int) -> str:
        return Scouter.concat_with_random_url_ending(Scouter.get_random_combination(comboFuel, maxCombos))

    @staticmethod
    def concat_combinations(list1: List[str], list2: List[str], list3: List[str]) -> DrawRnd:
        combinations = []
        for item1 in list1:
            for item2 in list2:
                for item3 in list3:
                    combinations.append(item1 + item2 + item3)
        random.shuffle(combinations)
        result: DrawRnd = DrawRnd()
        for item in combinations:
            if not item.startswith('.'):
                result.addElement(item)
        return result

    @staticmethod
    def check_url_operational(self: str) -> bool:
        # Ensure the URL has a scheme (http or https)
        parsed_url = urlparse(self)
        if not parsed_url.scheme:
            self = 'http://' + self
            parsed_url = urlparse(self)

        # Ensure the URL has a netloc (domain)
        if not parsed_url.netloc:
            self = 'http://www.' + self

        try:
            response = requests.get(self, allow_redirects=True, timeout=5)  # Add a timeout
            return response.status_code // 100 == 2
        except requests.RequestException:
            return False


class DiBrowser(Skill):
    def __init__(self):
        super().__init__()
        self.cmdBreaker: AXCmdBreaker = AXCmdBreaker("surf")
        self._str1: str = ""
        self.regexUtil = RegexUtil()
        # URL guessing
        self.rndURL: str = "yotamarker.com"
        self.siteCombos: DrawRnd = Scouter.concat_combinations(site_fuel, site_fuel2, site_endings)

    def input(self, ear: str, skin: str, eye: str):
        if len(ear) == 0:
            return
        match ear:
            case "scout":
                Scouter.scout()
                self.setSimpleAlg("scouting")
                return
            case "inflate seeds":
                Scouter.getDeepLinks()
                self.setSimpleAlg("mezoflating")
                return
            case "gen web address" | "random url" | "generate web address" | "gen url" | "generate a web address":
                # gen random url (1 word URLs)
                self.rndURL = Scouter.generateRND_URL(site_fuel, 1)
                self.setSimpleAlg(f'how about {self.rndURL}')
                return
            case "next url" | "nxt url" | "next site":
                # iterate generated URL combos(2 word URLs) one by one
                self.rndURL = self.siteCombos.renewableDraw()
                self.setSimpleAlg(f'how about {self.rndURL}')
                return
            case "surf it":
                # surf last URL guessed
                webbrowser.open(self.rndURL)
                self.setSimpleAlg("trying")
                return
            case "check url" | "check web page" | "check site":
                # check if last generated URL address is operational
                temp1: str = f"{self.rndURL} is operational"
                if not Scouter.check_url_operational(self.rndURL):
                    temp1 = "site looks dead"
                self.setSimpleAlg(temp1)
                return
            case _:
                pass
        self._str1 = self.cmdBreaker.extractCmdParam(ear)
        if len(self._str1) > 0:
            # surf URL command: omit http:// or https://
            temp: str = self.regexUtil.extractRegex(r"^([a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+.*)$", self._str1)
            if len(temp) > 0:
                # open the site 'temp'
                webbrowser.open(temp)
                self.setSimpleAlg("done")
            self._str1 = ""


def add_DLC_skills(brain: Brain):
    brain.add_logical_skill(DiBrowser())
