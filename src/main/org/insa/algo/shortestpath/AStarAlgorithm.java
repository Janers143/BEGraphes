package org.insa.algo.shortestpath;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    protected ShortestPathSolution doRun() {
    	ShortestPathData data = getInputData();
    	return new ShortestPathSolution(data);
    }
}
