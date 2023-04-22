from utils.edit import DiffTokenizer
from utils.edit import construct_diff_sequence_with_con
def opentxt(hash_map, path):
    with open(path, 'r',encoding='UTF-8')as file:
        line1 = file.readline()
        count = 1
        while line1:
            print(count)
            count = count + 1
            method_comment = line1.split("#####")
            hash_map[method_comment[0]] = method_comment[1]
            line1 = file.readline()
        file.close()

if __name__ == '__main__':
    DiffTokenizer = DiffTokenizer()
    lists = DiffTokenizer.tokenize_diff("For more\ninformation, see Connection","For more\ninformation, see Connection Draining")
    lists=construct_diff_sequence_with_con(lists[0],lists[1])
    for i in lists:
        print(i)