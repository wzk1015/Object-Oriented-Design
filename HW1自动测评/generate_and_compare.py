#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Feb 26 14:41:31 2020

@author: yanhongxi
"""

import sympy
from xeger import Xeger
import os
import random
import re

class error(RuntimeError):
    def __init__(self, arg):
        self.args = arg

ROUND = 20
ITEM_NUM = 20
TERM_S = r'[+-]?(((([+-]?[1-9]\d{0,2}\*)|([+-]?))x(\*\*[+-]?[1-9]\d{0,2})?)|([+-][1-9]\d{0,2}))'
TERM_L = r'[+-](((([+-]?[1-9]\d{0,2}\*)|([+-]?))x(\*\*[+-]?[1-9]\d{0,2})?)|([+-][1-9]\d{0,2}))'
x = sympy.Symbol('x')
x_ = Xeger()


NAME = 'Derivative'     #主类的文件名，不带.java后缀

print("README:请将所有类用javac命令编译为.class，放在code目录下")

print('请输入随机多项式的项数：')
ITEM_NUM = -1 + int(input())

print('请输入循环次数：')
ROUND = int(input())


for i in range(ROUND):
    fx = x_.xeger(TERM_S)
    for j in range(ITEM_NUM):
        fx += x_.xeger(TERM_L)
    myAnswer = os.popen('echo ' + fx + '|java -cp "code" '+NAME)
    myAnswer = myAnswer.read().replace("\n","")
    
    check = r'[+-]?(((([+-]?[1-9]\d{0,2}\*)|([+-]?))x(\*\*[+-]?[1-9]\d{0,2})?)|([+-][1-9]\d{0,2}))([+-](((([+-]?[1-9]\d{0,2}\*)|([+-]?))x(\*\*[+-]?[1-9]\d{0,2})?)|([+-][1-9]\d{0,2})))*'
    if (myAnswer==""):
        print("Empty String!Probably exception encountered in java\n"+fx+"(expected)")
        raise error("")
        
    if (re.match(check,myAnswer)==None and re.match("[0-9]*",myAnswer)==None):
        print("Wrong Output Format!\n"+myAnswer+"(your answer)\n"+fx+"(expected)\n\n")
        raise error("")
    
    rNum = random.uniform(-10, 10)
    tureValue = sympy.diff(fx, 'x').evalf(subs = {x : rNum})
    myValue = sympy.sympify(myAnswer).evalf(subs = {x : rNum})
    if myValue == tureValue:
        print(fx + ' correct\n')
        continue
    else: 

        print("Wrong Answer!\n"+fx)
        raise error("")
print("Congratulations!All correct!")



