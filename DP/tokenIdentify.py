import re
# 注意每次运行的时候 要把write.txt清空
f = open("oldComment.txt")
line = f.readline()
while line:
    strs = line.split()
    for word in strs:
        word = word.replace('.', '')
        word = word.replace('?', '')
        word = word.replace(',', '')
        word = word.replace('!', '')
        splitted = re.sub('([A-Z][a-z]+)', r' \1', re.sub('([A-Z]+)', r' \1', word)).split()
        for word2 in splitted:
            with open("write.txt", "a") as file:
                file.write(word2.lower() + "\n")
    line = f.readline()

f = open("write.txt")
line = f.readline()
S = set()
while line:
    line=line.replace('\n','')
    if len(line) != 0:
        S.add(line)
    line=f.readline()
flag = True

f = open("newComment.txt")
line = f.readline()
while line:
    strs = line.split()
    for word in strs:
        word = word.replace('.', '')
        word = word.replace('?', '')
        word = word.replace(',', '')
        word = word.replace('!', '')

        splitted = re.sub('([A-Z][a-z]+)', r' \1', re.sub('([A-Z]+)', r' \1', word)).split()
        for word2 in splitted:
            if not S.__contains__(word2.lower()):
                flag = False
                break
    line = f.readline()
print(flag)
f.close()