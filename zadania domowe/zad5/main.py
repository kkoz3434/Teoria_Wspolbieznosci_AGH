import networkx as nx
import matplotlib.pyplot as plt
import numpy as np


class Production:
    def __init__(self, operation_string):
        self.name = operation_string.split(")")[0]
        tmp = operation_string.split(")")[1]
        self.left = tmp.split("<-")[0].replace(" ", "")
        self.right = tmp.split("<-")[1].replace(" ", "")

    def __repr__(self):
        return self.name

    def print(self):
        print(self.name)
        print(self.left)
        print(self.right)


def is_in_relation(production1, production2, relations_arr):
    if production1.name == production2.name:
        relations_arr.append([production1, production2])
    else:
        if production2.left in production1.right:
            relations_arr.append([production1, production2])

def is_not_in_relation(production1, production2, relations_arr):
    if production1.name != production2.name:
        if production2.left not in production1.right:
            relations_arr.append([production1, production2])

def make_productions_arr():
    global i
    for i in production_set:
        A.append(Production(i))


def make_D_array():
    global i
    for i in range(len(A)):
        for j in range(i, len(A)):
            is_in_relation(A[i], A[j], D)

def make_I_array():
    global i
    for i in range(len(A)):
        for j in range(i, len(A)):
            is_not_in_relation(A[i], A[j], I)


if __name__ == '__main__':
    D = []
    A = []
    I = []
    production_set = ["a)x<- x+y",
                      "b)y <-y + 2* z",
                      "c) x<- 3 * x + z",
                      "d) z <- y - z"
                      ]
    word = "baadcb"
    make_productions_arr()

    make_D_array()
    make_I_array()

    ## printing for debugging
    print("A=", A)
    print("w=", word)
    print("D=",D)
    print("I=", I)

