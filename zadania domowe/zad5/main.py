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

    def __eq__(self, other):
        if self.name == other.name and self.left == other.left and self.right == other.right:
            return True
        else:
            return False

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
            relations_arr.append([production2, production1])


def is_not_in_relation(production1, production2, relations_arr):
    if production1.name != production2.name:
        if production2.left not in production1.right:
            relations_arr.append([production1, production2])
            relations_arr.append([production2, production1])


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


def check_if_production_in_A(str, A):
    for l in A:
        if (l.name == str):
            return l
    return None


def convert_word_to_productions(word, A):
    result = []
    for i in word:
        result.append(check_if_production_in_A(i, A))
    return result


def check_dependency(prod1_arr, prod2):
    for prod1 in prod1_arr:
        for i in D:
            if prod1 == i[0] and prod2 == i[1]:
                return True
    return False


def check_single_dependency(prod1, prod2):
    for i in D:
        if prod1 == i[0] and prod2 == i[1]:
            return True
    return False


def make_FNF_array():
    result = []
    i = 0
    while i < len(word_prod):
        result.append([word_prod[i]])
        for j in range(i + 1, len(word_prod)):
            if not check_dependency(result[len(result) - 1], word_prod[j]):
                result[len(result) - 1].append(word_prod[j])
                i += 1
            else:
                break
        i += 1

    return result


def make_empty_graph():
    graph = []
    for i in range(len(word_prod)):
        graph.append([])
        for j in range(len(word_prod)):
            graph[i].append(0)
    return graph


def make_word_full_graph(word_prod, graph):
    for i in range(len(word_prod)):
        for j in range(i + 1, len(word_prod)):
            if check_single_dependency(word_prod[i], word_prod[j]):
                graph[i][j] = 1


def print_arr(arr):
    for i in arr:
        print(i)
    print()


def find_way(graph, word_prod, start, end, curr, prev):
    if prev != start and curr == end:
        graph[start][end] = 0
    else:
        for i in range(curr + 1, len(graph)):
            if graph[curr][i] == 1:
                find_way(graph, word_prod, start, end, i, curr)


def reduce_graph(graph, word_prod):
    for i in range(0, len(graph)):
        for j in range(i + 1, len(graph)):
            if graph[i][j] == 1:
                find_way(graph, word_prod, i, j, i, None)
                # print_arr(graph)


def show_graph_with_labels(graph, word):
    graph_list = []
    for i in range(len(graph)):
        for j in range(len(graph)):
            if graph[i][j] == 1:
                graph_list.append((i, j))

    nodes_arr = []
    for i in word:
        nodes_arr.append(i)

    G = nx.DiGraph()
    G.add_edges_from(graph_list)
    nx.draw(G, with_labels=True, font_weight='bold')
    plt.show()


def make_FNF_from_graph(graph_cpy):
    def check_column(column):
        for i in range(len(graph_cpy)):
            if graph_cpy[i][column] == 1:
                return False
        return True

    def clear_row(row):
        for i in range(len(graph_cpy)):
            graph_cpy[row][i] = 0

    FNF_counter = 0
    FNF = []
    FNF_taken = []
    while (FNF_counter < len(graph_cpy)):
        tmp = []
        to_clear = []
        for i in range(len(graph_cpy)):
            if i not in FNF_taken and check_column(i):
                tmp.append(word_prod[i])
                FNF_taken.append(i)
                to_clear.append(i)
        for i in to_clear:
            clear_row(i)
        FNF.append(tmp)
        FNF_counter += len(tmp)
    return FNF


# mmm so many useful functions above me <3
if __name__ == '__main__':
    D = []
    A = []
    I = []
    production_set = ["a)x<- x+y",
                      "b)y <-y + 2 * z",
                      "c) x<- 3 * x + z",
                      "d) z <- y - z"
                      ]
    word = "baadcb"

    make_productions_arr()

    make_D_array()

    make_I_array()

    # creating production array from given word
    word_prod = convert_word_to_productions(word, A)

    # making FNF greedy
    FNF = make_FNF_array()

    # making empty matrix
    graph = make_empty_graph()

    # making graph with every edge possible
    make_word_full_graph(word_prod, graph)

    # reducing
    reduce_graph(graph, word)

    # making FNF from graph - topology sorting - find nodes without inputs and add to one FNF subclass
    graph_cpy = [row[:] for row in graph]
    FNF_graph = make_FNF_from_graph(graph_cpy)

    # printing
    print("A=", A)
    print("w=", word_prod)
    print("D=", D)
    print("I=", I)
    print("greedy FNF=", FNF)
    print("graph FNF=", FNF_graph)
    print("Reduced Graph Matrix: ")
    print_arr(graph)

    # show graph
    show_graph_with_labels(graph, word)
