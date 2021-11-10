import numpy as np
import matplotlib.pyplot as plt


def parse_file(path):
    x = []
    y = []
    f = open(path, "r")
    for i in f:
        tmp = i.split()
        x.append(float(tmp[0]))
        y.append(float(tmp[1]))

    return x, y


if __name__ == '__main__':
    x1, y1 = parse_file("2dane_100_cond.txt")
    x2, y2 = parse_file("2dane_100_twoForks.txt")
    x3,y3 = parse_file("2dane_100_asym.txt")
    for i in range(len(x2)):
        x2[i] = x2[i]+0.25

    for i in range(len(x3)):
       x3[i] = x3[i]+0.50

    print(sum(y1)/len(y1))
    print(sum(y2) / len(y2))
    print(sum(y3) / len(y3))

    plt.bar(x1, y1, label="waiter", width=0.25, )
    plt.bar(x2, y2, label="twoForks",width=0.25)
    plt.bar(x3, y3, label="asym", width=0.25)

    plt.legend()
    plt.ylabel("Ms time")
    plt.xlabel("AVG:\n waiter = 1.2919 \ntwoForks = 0.888 \nasym = 1.196")
    plt.title("JS, N=100")

    plt.show()
