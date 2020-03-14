## OO hw3 自动对拍程序

**by wzk**



### 文件位置

将两个`.py`文件和`projects`文件夹放在同一目录下，需要运行的所有文件放在projects下的目录下

（例：`/projects/wzk/MainClass.java`）

注意以`.`开头的文件夹会被自动忽略

### 需调整的参数：

（在`HW3BeatMatching.py`文件开头）

**MODE**： 若为`.java`则编译+运行，若为`.class`则直接运行（需要提前进行编译，将.class放在目录下）

注：若文件包含package，可通过idea-refactor放在同一目录下再进行build

**FILE_NAME_LIST**：每个project的主类名，不含后缀名



### 使用方式：

运行`HW3BeatMatching.py`，根据提示进行操作即可。

**1**： 自动生成数据测试。可以调整`HW3_generator.py`对产生的数据进行调整（默认配置的指数在0-9之间，满足互测要求）

**2**：手动输入数据，注意不会对输入数据的format进行检查，仅会检查长度

**3**：自动从文件`in.txt`读取，每一行作为一个fx（自动去除换行符），空行自动跳过，读取到“END”行则会自动停止

**4**：自动测试特定目标，与1相似。注意输入的目标名称为`projects`目录下的文件夹名，例如示例的name

### TIPS

只能生成正确格式的数据，WF数据无法进行检验。因此只要输出`WRONG FORMAT!`即会出现警告

两个文件（`in.txt`和`Wrong_log.txt`）应和.py同目录

不能产生含有前导0的数据

计算长度时自动剔除了空格