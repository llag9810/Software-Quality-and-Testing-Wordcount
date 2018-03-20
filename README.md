# 软件质量与测试作业

作业简述：根据WordCount的需求描述，先编程实现，再编写单元测试，最后撰写博客。
 
# WordCount 需求说明
WordCount的需求可以概括为：对程序设计语言源文件统计字符数、单词数、行数，统计结果以指定格式输出到默认文件中，以及其他扩展功能，并能够快速地处理多个文件。

可执行程序命名为：wc.exe，该程序处理用户需求的模式为：

wc.exe [parameter] [input_file_name]
存储统计结果的文件默认为result.txt，放在与wc.exe相同的目录下。

## 运行环境
Windows 10 64bit

## 基本功能
wc.exe -c file.c     //返回文件 file.c 的字符数
wc.exe -w file.c     //返回文件 file.c 的单词总数
wc.exe -l file.c     //返回文件 file.c 的总行数
wc.exe -o outputFile.txt     //将结果输出到指定文件outputFile.txt
注意：

空格也算字符。

由空格或逗号分割开的都视为单词，且不做单词的有效性校验，例如：thi#,that视为用逗号隔开的2个单词。

-c, -w, -l参数可以共用同一个输入文件，形如：wc.exe –w –c file.c 。

-o 必须与文件名同时使用，且输出文件必须紧跟在-o参数后面，不允许单独使用-o参数。 

## 扩展功能
wc.exe -s            //递归处理目录下符合条件的文件
wc.exe -a file.c     //返回更复杂的数据（代码行 / 空行 / 注释行）
wc.exe -e stopList.txt  // 停用词表，统计文件单词总数时，不统计该表中的单词
[file_name]: 文件或目录名，可以处理一般通配符。
