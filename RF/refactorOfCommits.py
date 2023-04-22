import urllib.request
import ssl
import json
def functionname(gitURL,commitId):

    context = ssl._create_unverified_context()
    url = "https://rminer.encs.concordia.ca:8000/RefactoringMiner?gitURL="+gitURL+"&commitId="+commitId+"&timeout=60"
    proxies = {'http': '218.86.128.100:8118'}
    proxy_support = urllib.request.ProxyHandler(proxies)
    opener = urllib.request.build_opener(proxy_support)
    urllib.request.install_opener(opener)


    request = urllib.request.Request(url)
    response = urllib.request.urlopen(url=request, context=context)

    response = response.read().decode('utf-8')

    data=json.loads(response)

    filename = 'refactoring_valid_0-2499.txt'
    with open(filename,'a') as f:
        f.write("----"+gitURL)
        f.write("\t"+commitId+"\n")
        for item in data['commits'][0]['refactorings']:
            f.write(item['description']+"\n")
    f.close()
with open("E:\\JIT dataset\\dataset\\commit_fullName_valid.txt", 'r') as f:
    for i in range(2500):

        line_data = f.readline()
        # if 0 <= i <= 29999:
        #     continue
        if line_data:
            print(i)
            line_data=line_data.replace("\n","")
            data = line_data.split("#####")
            try:
                functionname("https://github.com/"+data[0]+".git",data[1])
            except:
                print("An exception occurred")