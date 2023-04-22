import re
import nltk
from operator import *
import json

def camel_case_split(identifier):
    return re.sub(r'([A-Z][a-z])', r' \1', re.sub(r'([A-Z]+)', r' \1', identifier)).strip().split()

def tokenize_identifier_raw(token, keep_underscore=True):
    regex = r'(_+)' if keep_underscore else r'_+'
    id_tokens = []
    for t in re.split(regex, token):
        if t:
            id_tokens += camel_case_split(t)
    # note: do not use lowercase!
    return list(filter(lambda x: len(x) > 0, id_tokens))
def tokenize_identifier(token, with_con=False):
    if with_con:
        id_tokens = " <con> ".join(tokenize_identifier_raw(token, keep_underscore=True)).split()
    else:
        id_tokens = [t.lower() for t in tokenize_identifier_raw(token, keep_underscore=False)]
    return id_tokens
def tokenize_text(text):
    str_tokens = []
    nltk_tokenized = " ".join(nltk.word_tokenize(text))
    # split according to punctuations
    # string.punctuation
    # NOTE: do not care _, which will be taken care of by tokenized_identifier
    content_tokens = re.sub(r'([-!"#$%&\'()*+,./:;<=>?@\[\\\]^`{|}~])', r' \1 ', nltk_tokenized).split()
    for t in content_tokens:
        str_tokens += tokenize_identifier(t)
    return str_tokens
def mm():
    str_tokens0 = tokenize_text("Marks this message as timed out. Confirmable messages in particular might timeout.")
    str_tokens00 = []
    for x in str_tokens0:
        str_tokens00.append(x.lower())
    str_tokens1 = tokenize_text("Marks this message as timed out. Confirmable messages in particular might time out.")
    str_tokens11 = []
    for x in str_tokens1:
        str_tokens11.append(x.lower())
    print(str_tokens00)
    print(str_tokens11)
if __name__ == '__main__':
    count=0
    sum=0
    lists=[]
    with open('E:\\CUP\\dataset\\valid_text.txt', 'r') as f:
        lines = f.readlines()
        for line in lines:
            count=count+1
            docs=line.split("#####")
            str_tokens0=tokenize_text(docs[0])
            str_tokens00 = []
            for x in str_tokens0:
                str_tokens00.append(x.lower())
            str_tokens1=tokenize_text(docs[1])
            str_tokens11=[]
            for x in str_tokens1:
                str_tokens11.append(x.lower())
            if eq(str_tokens00,str_tokens11):
                lists.append(count)
                sum=sum+1
                print(count)
    with open("E:\\CUP\\dataset\\valid_new_rule4.jsonl", "w") as file:
        with open("E:\\CUP\\dataset\\valid_new2.jsonl", 'r') as f:
            count = 1
            try:
                while True:
                    line_data = f.readline()
                    if line_data:
                        if count not in lists:
                            file.write(line_data)
                        count = count + 1
                    else:
                        break
            except Exception as e:
                print(e)
                f.close()
            f.close()
        file.close()

