# Project description #
## Objectives ##
The aim of this project was to ilustrate algorithm solving Travelling Salesmam Problem (TSP), using Brach and Bound algorithm. Precise description of Travelling Salesman Project and Branch and Bound algorithm can be found on: http://en.wikipedia.org/wiki/Travelling_salesman_problem and http://en.wikipedia.org/wiki/Branch_and_bound respectively.

## Brief summary of TSP problem ##
Having N towns, we need to find the _shortest path_, starting from one town, passing all towns and going back to the starting point. Restriction: Salesman can be in each town once only.

## Results ##
The result of Branch and Bound algorithm is a 'cost' of the _shortest path_ and the order in which towns should be visited to finish the journey in the shortest time.

The program is devided into three modules - first one gives only the results (distance and order), the second one gives results, decision tree and the final costs' array. The most sophisticated is the third one, which ineractively shows the whole process of solving the problem.

## Used technologies ##
The program was written in **Java**, using **Jung** library.
**Java Universal Network/Graph Framework** is a free library for graph visualization and can be downloaded from http://jung.sourceforge.net/index.html.