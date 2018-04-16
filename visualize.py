import networkx as nx
import matplotlib.pyplot as pyplot

g=nx.read_edgelist('facebook_combined.txt',create_using=nx.Graph(),nodetype=int)

print(nx.info(g))

sp=nx.spring_layout(g)

plt.axis('off')

nx.draw_network(g,pos=sp,widh_label=False,node_size=35)

plt.show()