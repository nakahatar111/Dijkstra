class checkWeightedGraph
{
	static java.util.ArrayList<String> expectedResults = new java.util.ArrayList<>(); 
	public static void main(String[] args)
	{
		getExpectedResults();
		
		String[] filenames = new String[] {"sample_weighted_graph_1a.txt", 
				"sample_weighted_graph_1a.txt", 
				"sample_weighted_graph_2a.txt", 
				"sample_weighted_graph_2a.txt", 
				"sample_weighted_graph_2a.txt", 
				"sample_weighted_graph_4a.txt", 
				"sample_weighted_graph_5a.txt", 
				"sample_weighted_graph_7.txt", 
				"sample_weighted_graph_7.txt", 
				"sample_weighted_graph_7.txt", 
				"sample_weighted_graph_8.txt", /* extra credit */
				"sample_weighted_graph_8.txt", /* extra credit */
				"sample_weighted_graph_4.txt", /* extra credit */
				"sample_weighted_graph_5.txt"};/* extra credit */
		int[] fromVertices = new int[] {1, 13,  0,  1, 10, 0, 0,  1, 1, 2, 1, 2, 0, 0};
		int[] toVertices = new int[]  {13,  0, 20, 20,  0, 1, 2, 15, 2, 8, 2, 8, 1, 2};
		
		int lineNumber = -1;
		
		String finalResults = "";
		
		for( int i = 0; i < filenames.length; i++ )
		{
			System.out.println("processing " + filenames[i]);
			WeightedGraphFunctions g = getGraph(filenames[i]);
			System.out.println("\t" + "checking " + fromVertices[i] + " -> " + toVertices[i]);
			lineNumber = lineNumber+1;
			lineNumber = lineNumber+1;
			try
			{
				String currentTestResults = "";
				boolean hasPath = g.hasPath(fromVertices[i], toVertices[i]);
				double cost = g.getMinimumWeight(fromVertices[i], toVertices[i]);
				EdgeWithWeight[] path = g.getPath(fromVertices[i], toVertices[i]);
				String result1 = "\t\t" + "hasPath = " + hasPath;
				lineNumber = lineNumber+1;
				String expectedResult1 = expectedResults.get(lineNumber);
				if( expectedResult1.equals(result1) )
				{
					currentTestResults = currentTestResults + "\t0";
				}
				else
				{
					currentTestResults = currentTestResults + "\t1";
				}
				System.out.println(result1);
				String result2 = "\t\t" + "cost = " + cost;
				lineNumber = lineNumber+1;
				String expectedResult2 = expectedResults.get(lineNumber);
				if( expectedResult2.equals(result2) )
				{
					currentTestResults = currentTestResults + "\t0";
				}
				else
				{
					currentTestResults = currentTestResults + "\t1";
				}
				System.out.println(result2);
				String result3 = "\t\t" + "path = ";
				lineNumber = lineNumber+1;
				String expectedResult3 = expectedResults.get(lineNumber);
				for( int j = 0; j < path.length; j++ )
				{
					if( j > 0 )
					{
						result3 = result3 + ",";
					}
					result3 = result3 + path[j];
				}
				System.out.println(result3);
				if( expectedResult3.equals(result3) )
				{
					currentTestResults = currentTestResults + "\t0";
				}
				else
				{
					currentTestResults = currentTestResults + "\t1";
				}
				System.out.println("currentTestResults = " + currentTestResults);
				finalResults = finalResults + currentTestResults;
			}
			catch(Exception e)
			{
				System.out.println("currentTestResults = \t1\t1\t1");
				finalResults = finalResults + "\t1\t1\t1";
			}
		}
		System.err.println("finalResults = " + finalResults);
	}
	
	public static WeightedGraphFunctions getGraph(String filename)
	{
		WeightedGraphFunctions g = new nakahata_WeightedGraph();
		
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(filename)));
			String inn;
			
			// get rid of the first line
			inn = input.readLine();
			
			// get the list of vertices
			inn = input.readLine();
			inn = inn.substring(4);
			java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "{},");
			while( st.hasMoreTokens() )
			{
				int newVertex = Integer.parseInt(st.nextToken());
				g.addVertex(newVertex);
			}
			
			// get the list of edges
			inn = input.readLine();
			inn = inn.substring(4);
			st = new java.util.StringTokenizer(inn, "{}");
			inn = st.nextToken();
			st = new java.util.StringTokenizer(inn, "(),");
			while( st.hasMoreTokens() )
			{
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				double w = Double.parseDouble(st.nextToken());
				g.addWeightedEdge(from, to, w);
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		return g;
	}
	
	private static void getExpectedResults()
	{
		expectedResults.add("processing sample_weighted_graph_1a.txt");
		expectedResults.add("\tchecking 1 -> 13");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 6.0");
		expectedResults.add("\t\tpath = (1,5,1.0),(5,6,1.0),(6,9,2.0),(9,13,2.0)");
		expectedResults.add("processing sample_weighted_graph_1a.txt");
		expectedResults.add("\tchecking 13 -> 0");
		expectedResults.add("\t\thasPath = false");
		expectedResults.add("\t\tcost = NaN");
		expectedResults.add("\t\tpath = ");
		expectedResults.add("processing sample_weighted_graph_2a.txt");
		expectedResults.add("\tchecking 0 -> 20");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 4.0");
		expectedResults.add("\t\tpath = (0,5,1.0),(5,6,0.5),(6,18,2.0),(18,20,0.5)");
		expectedResults.add("processing sample_weighted_graph_2a.txt");
		expectedResults.add("\tchecking 1 -> 20");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 6.5");
		expectedResults.add("\t\tpath = (1,4,1.0),(4,7,1.0),(7,10,1.0),(10,13,0.5),(13,16,0.5),(16,19,1.0),(19,20,1.5)");
		expectedResults.add("processing sample_weighted_graph_2a.txt");
		expectedResults.add("\tchecking 10 -> 0");
		expectedResults.add("\t\thasPath = false");
		expectedResults.add("\t\tcost = NaN");
		expectedResults.add("\t\tpath = ");
		expectedResults.add("processing sample_weighted_graph_4a.txt");
		expectedResults.add("\tchecking 0 -> 1");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 3.0");
		expectedResults.add("\t\tpath = (0,3,1.0),(3,4,1.0),(4,1,1.0)");
		expectedResults.add("processing sample_weighted_graph_5a.txt");
		expectedResults.add("\tchecking 0 -> 2");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 3.0");
		expectedResults.add("\t\tpath = (0,2,3.0)");
		expectedResults.add("processing sample_weighted_graph_7.txt");
		expectedResults.add("\tchecking 1 -> 15");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 12.0");
		expectedResults.add("\t\tpath = (1,349,1.0),(349,160,3.0),(160,789,3.0),(789,915,2.0),(915,245,2.0),(245,15,1.0)");
		expectedResults.add("processing sample_weighted_graph_7.txt");
		expectedResults.add("\tchecking 1 -> 2");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 13.0");
		expectedResults.add("\t\tpath = (1,405,1.0),(405,436,3.0),(436,328,3.0),(328,165,1.0),(165,555,2.0),(555,818,1.0),(818,2,2.0)");
		expectedResults.add("processing sample_weighted_graph_7.txt");
		expectedResults.add("\tchecking 2 -> 8");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 16.0");
		expectedResults.add("\t\tpath = (2,797,1.0),(797,805,1.0),(805,93,2.0),(93,500,1.0),(500,407,1.0),(407,592,1.0),(592,374,1.0),(374,430,1.0),(430,327,2.0),(327,575,3.0),(575,498,1.0),(498,8,1.0)");
		expectedResults.add("processing sample_weighted_graph_8.txt");
		expectedResults.add("\tchecking 1 -> 2");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 13.0");
		expectedResults.add("\t\tpath = (1,405,1.0),(405,436,3.0),(436,328,3.0),(328,165,1.0),(165,555,2.0),(555,818,1.0),(818,2,2.0)");
		expectedResults.add("processing sample_weighted_graph_8.txt");
		expectedResults.add("\tchecking 2 -> 8");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 16.0");
		expectedResults.add("\t\tpath = (2,797,1.0),(797,805,1.0),(805,93,2.0),(93,500,1.0),(500,407,1.0),(407,592,1.0),(592,374,1.0),(374,430,1.0),(430,327,2.0),(327,575,3.0),(575,498,1.0),(498,8,1.0)");
		expectedResults.add("processing sample_weighted_graph_4.txt");
		expectedResults.add("\tchecking 0 -> 1");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 3.0");
		expectedResults.add("\t\tpath = (0,3,1.0),(3,4,1.0),(4,1,1.0)");
		expectedResults.add("processing sample_weighted_graph_5.txt");
		expectedResults.add("\tchecking 0 -> 2");
		expectedResults.add("\t\thasPath = true");
		expectedResults.add("\t\tcost = 3.0");
		expectedResults.add("\t\tpath = (0,2,3.0)");
	}
}
