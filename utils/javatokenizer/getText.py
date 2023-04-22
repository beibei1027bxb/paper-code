import json

if __name__ == '__main__':
    with open("E:\\CUP\\dataset\\original_train.jsonl", "r") as f:
        with open("E:\\CUP\\dataset\\original_train2.jsonl", "w") as file:
            count = 0
            for i in range(73287):
                line_data = f.readline()
                if line_data:
                    count = count + 1
                    print(count)
                    file.write(line_data)
                else:
                    break
