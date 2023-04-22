import json
# if __name__ == '__main__':
#     with open('E:\\CUP\\dataset\\9_.jsonl', 'r') as f:
#         with open('E:\\CUP\\dataset\\9_withoutRefactor.jsonl', 'w') as f0:
#             line = f.readline()
#             length = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0".__len__()
#             while line:
#                 data = json.loads(line)
#                 data['src_method'] = str(data['src_method'])[length:]
#                 data['dst_method'] = str(data['dst_method'])[length:]
#                 data['code_change_seq'] = data['code_change_seq'][46:]
#                 f0.write(json.dumps(data)+"\n")
#                 line = f.readline()
#             f0.close()
if __name__ == '__main__':
    with open('E:\\CUP\\dataset\\7_withoutRefactor.jsonl', 'r') as f:
        with open('E:\\CUP\\dataset\\last_7_withoutRefactor.jsonl', 'w') as f0:
            line =f.readline()
            i = 1
            while line:
                if i<=5000:
                    line = f.readline()
                    i = i + 1
                else:
                    f0.write(line)
                    line = f.readline()
                    i = i + 1
            f0.close()
        f.close()