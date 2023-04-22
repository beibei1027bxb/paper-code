import re
from nltk.corpus import words

# 注意每次运行的时候 要把write.txt清空
f = open("oldComment.txt")
line = f.readline()
undefined_wors = {}

while line:
    strs = line.split()
    i = 0
    for word in strs:
        word = word.replace('.', '')
        word = word.replace('?', '')
        word = word.replace(',', '')
        word = word.replace('!', '')
        splitted = re.sub('([A-Z][a-z]+)', r' \1', re.sub('([A-Z]+)', r' \1', word)).split()

        for word2 in splitted:
            i = i + 1
            if not word2.lower() in words.words():
                undefined_wors[word2] = i
    line=f.readline()

if undefined_wors.__len__()!=0:
    f = open("newComment.txt")
    line = f.readline()
    strs = line.split()
    for key in undefined_wors:
        index=undefined_wors[key]
        i = 0
        for word in strs:
            word = word.replace('.', '')
            word = word.replace('?', '')
            word = word.replace(',', '')
            word = word.replace('!', '')
            splitted = re.sub('([A-Z][a-z]+)', r' \1', re.sub('([A-Z]+)', r' \1', word)).split()

            for word2 in splitted:
                i = i + 1
                if i==index:
                    if key.__eq__(word2):
                        print(True)
                    else:print(False)
    #判断是否属于方法中的标识符
