import java.util.*;

public class Portfolio3 {

    public static void main(String[] args) {
        //part 1
        Graph graph = new MatrixGraph(27);
        GraphAlgorithms.readGraph(graph, "combi.txt");
        System.out.println("\nGraph represented as an Adjacency Matrix Graph:\n");
        GraphAlgorithms.printGraph(graph);

        //part 2
        Vertex vertex = graph.vertex("DATA");
        Collection<Vertex> visited = GraphAlgorithms.visitDepthFirst(graph, vertex);
        System.out.println("\nModules visited: " + visited);
        System.out.println("Total vertices in the graph: " + graph.vertices().size());
        System.out.println("Vertices visited: " + visited.size()+"\n");
        //System.out.print(graph.edges().size()); // total edges

        //part 3
        List<Vertex> vertices = new ArrayList<>(visited);//convert the Collection into a list to sort it out
        Collections.sort(vertices,(Vertex v1,Vertex v2)-> ((graph.outEdge(v1).size()) - (graph.outEdge(v2).size())));
        System.out.println("List of all the subject modules sorted by their number of combinations:\n");
        for(Vertex v : vertices) {
            System.out.println(v + " " + graph.outEdge(v).size());
        }

        //part 4
        String[] slot1 = {"IS", "KEMI", "PSYK"};
        String[] slot2 = {"HIST", "PLANBY"};
        String[] slot3 = {"VIRK"};
        String[] slot4 = {"JOURNALIST", "PÆD"};
        String[] slot5 = {"ENG", "GEO", "MAT", "SUNDHFR"};
        String[] slot6 = {"PA", "PERF"};
        String[] slot7 = {"FYSIK", "INFORMATIK", "KULTUR", "MOLBIO"};
        String[] slot8 = {"ARBLIV"};
        String[] slot9 = {"DANSK", "TEKSAM"};
        String[] slot10 = {"DATA", "MEDBIO", "SOC"};
        String[] slot11 = {"FILO"};
        String[] slot12 = {"KOMM", "MILBIO"};
        String[][] slots = {slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12};

        System.out.println("\nSuggested solution to the scheduling problem:\n");
        for(String[]slot : slots){
            System.out.println(Arrays.deepToString(slot));
        }

        System.out.println("\nIs the suggested schedule a solution to the problem?:\n"+isASolution(graph, slots)); //run the test

        //part 5
        String[][] mon = {slot1, slot2, slot3};
        String[][] tue = {slot4, slot5, slot6};
        String[][] wed = {slot7, slot8};
        String[][] thu = {slot9, slot10};
        String[][] fri = {slot11, slot12};

        String[][][] week = {mon, tue, wed, thu, fri};

        //print the total number of students in adjacent modules
        System.out.println("\nCalculate how many students will be in adjacent timeslots using the ordering of groups above:\n");
        for(String[][]day : week){
            System.out.println("Total students on "+Arrays.deepToString(day)+ " " +numStud(graph, day));
        }
        System.out.println("\nRedo calculation for a reordering of the groups:\n");


        //------- redo the calculation for a reordering of the groups -----------
        Collections.shuffle(Arrays.asList(slots));  //shuffle the slotR

        String[][] monRand = {slots[0], slots[1], slots[2]};
        String[][] tueRand = {slots[3], slots[4], slots[5]};
        String[][] wedRand = {slots[6], slots[7]};
        String[][] thuRand = {slots[8], slots[9]};
        String[][] friRand = {slots[10], slots[11]};

        String[][][] weekRand = {monRand, tueRand, wedRand, thuRand, friRand};

        //print the new total number of students in adjacent modules after group reordering
        for(String[][]day : weekRand){
            System.out.println("Total students on "+Arrays.deepToString(day)+ " " +numStud(graph, day));
        }
    }

    //create a list of vertex
    private static ArrayList<Vertex> createVertex(Graph g, String[] modules){
        ArrayList<Vertex> listVertex = new ArrayList<>();
        for(String s : modules){
            listVertex.add(g.vertex(s));
        }
        return listVertex;
    }

    //to go through each group to check if they are connected
    private static boolean isConnected(Graph g, String[] s){
        ArrayList<Vertex> group = createVertex(g, s);
        if(s.length > 1)
            for(int i = 0; i < group.size(); i++){
                for(int j = i+1; j < group.size(); j++){
                    if(g.getWeight(group.get(i), group.get(j)) != null)
                        return true;
                }
            }
        else{
            return false;
        }
        return false;
    }

    //to check if whole schedule is a correct solution
    private static String isASolution(Graph g, String[][] sArray){
        for(String[] s : sArray){
            if(isConnected(g, s)){
                return "No, it's not a solution";
            }
        }
        return "Yes, it's a solution";
    }

    //calculate the students between two slots
    private static int adjStud(Graph g, ArrayList<Vertex> s1, ArrayList<Vertex> s2){
        int totalWeight = 0;
        for(int i=0; i<s1.size(); i++){
            for(int j=0; j<s2.size(); j++){
                if(g.getWeight(s1.get(i), s2.get(j)) != null){
                    totalWeight += g.getWeight(s1.get(i), s2.get(j));
                }
            }
        }
        return totalWeight;
    }

    //calculate the total of students for 2 slots at the time per day
    private static int numStud(Graph g, String[][] day){
        int totalWeight = 0;
        for(int i=1; i<day.length; i++){
            ArrayList<Vertex> group1 = createVertex(g, day[i]);
            ArrayList<Vertex> group2 = createVertex(g, day[i-1]);
            totalWeight += adjStud(g, group1, group2);
        }
        return totalWeight;
    }
}
