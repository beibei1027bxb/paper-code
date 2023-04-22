import re
import json


def typeToValue(type):
    l = 1
    switcher = {
        "Extract Method": 1,
        "Inline Method": 2,
        "Rename Method": 4,
        "Extract And Move Method": 8,
        "Extract Variable": 16,
        "Inline Variable": 32,

        "Parameterize Variable": 64,
        "Rename Variable": 128,
        "Rename Parameter": 256,
        "Replace Variable With Attribute": 512,
        "Merge Variable": 1024,
        "Merge Parameter": 2048,

        "Split Variable": 4096,
        "Split Parameter": 8192,
        "Change Variable Type": 16384,
        "Change Parameter Type": 32768,
        "Change Return Type": 65536,
        "Move And Rename Method": 131072,

        "Move And Inline Method": 262144,
        "Add Method Annotation": 524288,
        "Remove Method Annotation": 1048576,
        "Modify Method Annotation": 2097152,
        "Add Parameter Annotation": 4194304,
        "Remove Parameter Annotation": 8388608,

        "Modify Parameter Annotation": 16777216,
        "Add Variable Annotation": 33554432,
        "Remove Variable Annotation": 67108864,
        "Modify Variable Annotation": 134217728,
        "Add Parameter": 268435456,
        "Remove Parameter": 536870912,

        "Reorder Parameter": 1073741824,
        "Add Thrown Exception Type": l << 31,
        "Remove Thrown Exception Type": l << 32,
        "Change Thrown Exception Type": l << 33,
        "Change Method Access Modifier": l << 34,
        "Parameterize Attribute": l << 35,

        "Replace Attribute with Variable": l << 36,
        "Add Method Modifier": l << 37,
        "Remove Method Modifier": l << 38,
        "Add Variable Modifier": l << 39,
        "Remove Variable Modifier": l << 40,
        "Add Parameter Modifier": l << 41,

        "Remove Parameter Modifier": l << 42,
        "Localize Parameter": l << 43,
        "Replace Loop With Pipeline": l << 44,
        "Replace Anonymous With Lambda": l << 45,

    }
    return switcher.get(type, 0)


def isOne(type, num):
    hashmap = dict()

    hashmap["Extract Method"] = 0
    hashmap["Inline Method"] = 1
    hashmap["Rename Method"] = 2
    hashmap["Extract And Move Method"] = 3
    hashmap["Extract Variable"] = 4
    hashmap["Inline Variable"] = 5
    hashmap["Parameterize Variable"] = 6
    hashmap["Rename Variable"] = 7

    hashmap["Rename Parameter"] = 8
    hashmap["Replace Variable With Attribute"] = 9
    hashmap["Merge Variable"] = 10
    hashmap["Merge Parameter"] = 11
    hashmap["Split Variable"] = 12
    hashmap["Split Parameter"] = 13
    hashmap["Change Variable Type"] = 14
    hashmap["Change Parameter Type"] = 15

    hashmap["Change Return Type"] = 16
    hashmap["Move And Rename Method"] = 17
    hashmap["Move And Inline Method"] = 18
    hashmap["Add Method Annotation"] = 19
    hashmap["Remove Method Annotation"] = 20
    hashmap["Modify Method Annotation"] = 21
    hashmap["Add Parameter Annotation"] = 22
    hashmap["Remove Parameter Annotation"] = 23

    hashmap["Modify Parameter Annotation"] = 24
    hashmap["Add Variable Annotation"] = 25
    hashmap["Remove Variable Annotation"] = 26
    hashmap["Modify Variable Annotation"] = 27
    hashmap["Add Parameter"] = 28
    hashmap["Remove Parameter"] = 29
    hashmap["Reorder Parameter"] = 30
    hashmap["Add Thrown Exception Type"] = 31

    hashmap["Remove Thrown Exception Type"] = 32
    hashmap["Change Thrown Exception Type"] = 33
    hashmap["Change Method Access Modifier"] = 34
    hashmap["Parameterize Attribute"] = 35
    hashmap["Replace Attribute With Variable"] = 36
    hashmap["Add Method Modifier"] = 37
    hashmap["Remove Method Modifier"] = 38
    hashmap["Add Variable Modifier"] = 39

    hashmap["Remove Variable Modifier"] = 40
    hashmap["Add Parameter Modifier"] = 41
    hashmap["Remove Parameter Modifier"] = 42
    hashmap["Localize Parameter"] = 43
    hashmap["Replace Loop With Pipeline"] = 44
    hashmap["Replace Anonymous With Lambda"] = 45
    if ((num >> hashmap.get(type)) & 1) != 0:
        return False
    else:
        return True


if __name__ == '__main__':
    hashmap = dict()
    with open("E:\\pythonReptile\\refactoring_valid_2500-end-4.txt", 'r') as f:
        str0 = f.readline()
        count = 0
        while (str0 is not None) and (str0 != ""):
            count = count + 1
            print(count)
            if str0.startswith("----"):
                index = str0.index(".git")
                full_name = str0[23:index]
                commit_id = str0.split("\t")[1].replace("\n", "")
                hashmap[full_name + "#####" + commit_id] = dict()
                str0 = f.readline()
                while ((str0 is not None) and (str0 != "")) and (not str0.startswith("----")):
                    count = count + 1
                    print(count)
                    split = str0.split("#####")
                    type = split[0]
                    value = typeToValue(type)
                    stringIntegerHashMap = hashmap.get(full_name + "#####" + commit_id)
                    for i in range(1, len(split)):
                        if not stringIntegerHashMap.__contains__(split[i].replace("\n", "")):
                            stringIntegerHashMap[split[i].replace("\n", "")] = value
                        else:
                            if isOne(type, stringIntegerHashMap.get(split[i].replace("\n", ""))):
                                stringIntegerHashMap[split[i].replace("\n", "")] = stringIntegerHashMap.get(
                                    split[i].replace("\n", "")) + value
                    str0 = f.readline()
        f.close()
    with open("E:\\CUP\\dataset\\valid.jsonl", 'r') as f:
        with open("E:\\CUP\\dataset\\valid_2500-end.jsonl", 'w') as f0:
            str0 = f.readline()
            count = 0
            while (str0 is not None) and (str0 != ""):
                count = count + 1
                print(count)
                data = json.loads(str0)
                if data["full_name"] + "#####" + data["commit_id"] in hashmap:
                    hashmap2 = hashmap.get(data["full_name"] + "#####" + data["commit_id"])
                    result_new = []
                    result = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                    src_method_original = str(data["src_method"])
                    dst_method_original = str(data["dst_method"])
                    if len(hashmap2) == 0:
                        result_new = [str(x) for x in result]
                        data["src_method"] = " ".join(result_new) + src_method_original
                        data["dst_method"] = " ".join(result_new) + dst_method_original
                        f0.write(json.dumps(data) + "\n")
                        str0 = f.readline()
                        continue
                    for key in hashmap2:
                        regex = key.replace("(", "\(").replace(")", "\)").replace(".", "\.").replace("[","\[").replace("]","\]").replace(" ", ".{0,100}").replace("(","(.{0,100}")
                        regex = ".{0,100}" + regex[0:]

                        src_method = data["src_method"].replace("\n", "")
                        dst_method = data["dst_method"].replace("\n", "")
                        try:
                            result1 = re.match(regex, src_method)
                        except:
                            print("error occurred")
                        result11 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        try:
                            result2 = re.match(regex, dst_method)
                        except:
                            print("error occurred")
                        result22 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

                        if (result1 is not None) and (result1 != ""):
                            for i in range(46):
                                result11[45-i] = (hashmap2.get(key) >> i) & 1
                        if (result2 is not None) and (result2 != ""):
                            for i in range(46):
                                result22[45-i] = (hashmap2.get(key) >> i) & 1
                        for ii in range(46):
                            result[ii] = result11[ii] | result22[ii] | result[ii]
                        result_new = [str(x) for x in result]
                    data["src_method"] = " ".join(result_new) + src_method_original
                    data["dst_method"] = " ".join(result_new) + dst_method_original
                    f0.write(json.dumps(data)+"\n")

                str0 = f.readline()
            f0.close()
        f.close()

    # dictofFullandCOMMIT = dict()
    # with open("E:\\JIT dataset\\dataset\\train_rule2_rule4_rule1_rule3.jsonl", 'r') as f:
    #     count = 0
    #     try:
    #         while True:
    #             count = count + 1
    #             line_data = f.readline()
    #             if line_data:
    #                 data = json.loads(line_data)
    #                 if dictofFullandCOMMIT.get(data['full_name'] + "#####" + data['commit_id']) is not None:
    #                     item = dictofFullandCOMMIT.get(data['full_name'] + "#####" + data['commit_id'])
    #                     item.append(count)
    #                 else:
    #                     dictofFullandCOMMIT[data['full_name'] + "#####" + data['commit_id']] = [count]
    #             else:
    #                 break
    #     except Exception as e:
    #         print("error")
    #     f.close()
    # with open("E:\\pythonReptile\\refactoring_2000-3999-4.txt", 'r') as f:
    #     count = 0
    #     line_data = f.readline()
    #     try:
    #         while True:
    #             count = count + 1
    #             print(count)
    #             if line_data:
    #                 if line_data.startswith("----"):
    #                     index = line_data.index(".git")
    #                     full_name = line_data[23:index]
    #                     commit_id = line_data.split("\t")[1]
    #                     if dictofFullandCOMMIT[full_name + "#####" + commit_id] is not None:
    #                         with open("E:\\JIT dataset\\dataset\\train_rule2_rule4_rule1_rule3.jsonl", 'r') as f1:
    #                             dictofOne = dict()
    #                             while True:
    #                                 count2 = 1
    #                                 line = f1.readline()
    #                                 if count2 in dictofFullandCOMMIT[full_name + "#####" + commit_id]:
    #                                     src_method = data['src_method'].replace("(", "\(").replace(")", "\)").replace(
    #                                         ".", "\.")
    #                                     dst_method = data['dst_method'].replace("(", "\(").replace(")", "\)").replace(
    #                                         ".", "\.")
    #                                     dictofOne[full_name + "#####" + commit_id] = [src_method + "#####" + dst_method]
    #
    #
    #
    #             else:
    #                 break
    #
    #     except Exception as e:
    #         print("error")
    #     f.close()
