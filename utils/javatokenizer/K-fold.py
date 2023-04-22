
if __name__ == '__main__':
    with open('E:\\CUP\\dataset\\all-data.jsonl', 'r') as f:
        with open('E:\\CUP\\dataset\\9_.jsonl', 'w') as file:
            for i in range(90000):
                line = f.readline()
                if 0 <= i < 80000:
                    continue
                if line:
                    file.write(line)
