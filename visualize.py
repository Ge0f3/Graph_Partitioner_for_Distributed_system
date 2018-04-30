import networkx as nx
import matplotlib.pyplot as plt

g=nx.read_edgelist('HR_edges.txt',create_using=nx.Graph(),nodetype=int)

print(nx.info(g))

sp=nx.spring_layout(g)

plt.axis('off')

nx.draw_networkx(g,pos=sp,widh_label=False,node_size=35)

plt.show()