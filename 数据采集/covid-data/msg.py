import json
import requests
import codecs
import os


def get_data():
    name = open("name.txt", "r")
    mes = codecs.open("msg1.txt", "a", "utf-8")
    for item in name:
        item = item.replace("\n", "")
        # print(i)
        url = "https://i.snssdk.com/forum/ncov_data/?country_id=USA&country_name=%E7%BE%8E%E5%9B%BD&click_from=overseas_epidemic_tab_map&data_type=%5B6%5D&province_id=%5B%228400" + item + "%22%5D&src_type=country"
        data = requests.get(url)
        datar = json.loads(data.text)
        # print(datar["province_data"]["8400"+i])
        mes.write(datar["province_data"]["8400" + item])
        mes.write("\n")
    name.close()
    mes.close()


def translate_name():
    mes1 = open("msg1.txt", "r", encoding='UTF-8')
    mes = open("msg.txt", "a", encoding='UTF-8')
    rename = open("rename.txt", "r", encoding='UTF-8')
    dick = {}
    for name in rename:
        name = name.replace("\n", "")
        arr = name.split("\t")
        dick[arr[1]] = arr[0]
    # print(dick)
    for item in mes1:
        ijson = json.loads(item)
        if ijson["name"] in dick:
            ijson["name"] = dick[ijson["name"]]
            # print(dick[ijson["name"]])
            # print(ijson["name"])
        str = json.dumps(ijson)
        mes.write(str)
        mes.write("\n")
    mes.close()
    rename.close()
    mes1.close()


if __name__ == "__main__":
    os.remove("msg1.txt")
    os.remove("msg.txt")
    get_data()
    translate_name()
