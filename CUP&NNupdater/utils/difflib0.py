import difflib
from difflib import Differ
import json
from pprint import pprint
import re
from utils.edit import DiffTokenizer
from utils.edit import construct_diff_sequence_with_con
from utils.tokenizer import Tokenizer
def opentxt(hash_map, path):
    with open(path, 'r',encoding='UTF-8')as file:
        line1 = file.readline()
        while line1:
            method_comment = line1.split("#####")
            hash_map[method_comment[0]] = method_comment[1]
            line1 = file.readline()
def lf(filenameR,filenameW):
    with open("E:\\CUP\\dataset\\"+filenameR+"_.jsonl", 'r') as f:
        with open("E:\\"+filenameW+"_withContext.jsonl", 'w') as f3:
            s = set()
            s.add("if")
            s.add("for")
            s.add("else")
            s.add("toString")
            s.add("add")
            s.add("get")
            count = 1
            while True:
                line = f.readline()
                if line:
                    print(count)
                    count = count + 1
                    str_append = ""
                    data = json.loads(line)
                    text1 = str(data['src_method']).splitlines(keepends=False)
                    text2 = str(data['dst_method']).splitlines(keepends=False)
                    full_name = str(data['full_name'])
                    index = full_name.rindex("/")
                    full_name = full_name[index + 1:]
                    d = Differ()
                    result = list(d.compare(text1, text2))
                    for item in result:
                        if str(item).startswith("+") or str(item).startswith("-"):
                            regex = ".{0,50}\\(.{0,100}\\)"
                            try:
                                result = re.match(regex, str(item))
                                if (result is not None) and (result != ""):
                                    index = str(item).index("(")
                                    string = str(item)[1:index].strip()
                                    if string not in s:
                                        if string.__contains__("."):
                                            index0 = string.rindex(".")
                                            if string[index0 + 1:] not in s:
                                                print(string[index0 + 1:])
                                                hash_map = dict()
                                                opentxt(hash_map,"E:\\1500_comments\\" + full_name + ".txt")
                                                if hash_map.__contains__(string[index0 + 1:]):
                                                    str_append = str_append + hash_map[string[index0 + 1:]]

                                        else:
                                            if not string.__contains__(" "):
                                                if string not in s:
                                                    print(string)
                                                    hash_map = dict()
                                                    opentxt(hash_map, "E:\\1500_comments\\" + full_name + ".txt")
                                                    if hash_map.__contains__(string):
                                                        str_append = str_append + hash_map[string]

                            except:
                                print("error occurred")
                    if str_append:
                        data['src_javadoc'] = str(data['src_javadoc']) + " - - - " + str_append
                        data['src_desc'] = str(data['src_desc'])+" - - - "+str_append
                        data['src_desc_tokens'] = Tokenizer.tokenize_desc_with_con(str(data['src_desc']))
                        DiffTokenizer2 = DiffTokenizer()
                        lists = DiffTokenizer2.tokenize_diff(str(data['src_desc']),str(data['dst_desc']))
                        data['desc_change_seq'] = construct_diff_sequence_with_con(lists[0],lists[1])
                    f3.write(json.dumps(data) + "\n")

                else:
                    break

if __name__ == '__main__':
    for i in range(9):
        print("file"+str(i+1))
        lf(filenameR=str(i+1),filenameW=str(i+1))


